package newsapi.util.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * A proxy servlet that passes all request parameters through to a 3rd party service (in this case Mashery).
 * Used to get around the cross-origin issues with Javascript based web-apps.
 * <p/>
 * <b>Ensure you set MasheryDeveloperKey to your Mashery developer key</b>
 * <p/>
 * Usage: Add a servlet mapping in your web.xml as below:
 * <p/>
 * 
 * <pre>
 * {@code
 * 	<servlet>
 * 	<servlet-name>proxy</servlet-name>
 * 	<servlet-class>newsapi.util.servlet.ProxyServlet</servlet-class>
 * </servlet>
 * <servlet-mapping>
 * 	<servlet-name>proxy</servlet-name>
 * 	<url-pattern>/proxy/*</url-pattern>
 * </servlet-mapping>
 * </pre>
 * 
 * }
 * <p/>
 * All request to /proxy/* will now get forwarded to Mashery.
 */

public class ProxyServlet extends HttpServlet {
	private static final String MasheryHost = "newsaustralia.api.mashery.com";
	private static final String MasheryDeveloperKey = "REPLACE_WITH_YOUR_MASHERY_DEVELOPER_KEY";

	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final List<String> IGNORE_HEADERS = Arrays.asList("Host");
	private static final Logger Logger = java.util.logging.Logger.getLogger(ProxyServlet.class.getName());

	private static final long serialVersionUID = -9089088104807217936L;

	static {
		HttpsURLConnection.setDefaultHostnameVerifier(new CustomizedHostnameVerifier());
	}

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpURLConnection connection = getConnection(req);

			copyInputMethod(req, connection);
			copyInputHeaders(req, connection);
			copyInputBody(req, connection);

			/*
			 * Read response into view to proxy back to requester
			 */

			int status = connection.getResponseCode();
			res.setStatus(status);
			Logger.fine("Remote service responded with status: " + status);

			Map<String, List<String>> headers = connection.getHeaderFields();

			byte[] body = IOUtils.toByteArray((status >= 200 && status <= 299) ? connection.getInputStream() : connection.getErrorStream());
			Logger.fine("Response body: " + new String(body));

			connection.disconnect();

			sendResponse(res, status, headers, body);
		} catch (MalformedURLException mue) {
			Logger.severe(mue.getMessage());
			sendResponse(res, 500, mue.getMessage().getBytes(UTF_8));
		} catch (IOException ioe) {
			Logger.severe(ioe.getMessage());
			sendResponse(res, 500, ioe.getMessage().getBytes(UTF_8));
		}
	}

	private String buildTargetUrl(HttpServletRequest req) {
		String protocol = req.isSecure() ? "https" : "http";
		StringBuilder sb = new StringBuilder(protocol + "://");

		sb.append(MasheryHost);
		sb.append(req.getRequestURI());
		sb.append("?api_key=" + MasheryDeveloperKey);

		String qs = req.getQueryString();
		if (qs != null) {
			sb.append("&" + qs);
		}

		return remapUrl(sb.toString());
	}

	private String remapUrl(String proxiedUrl) {
		String endpoint = getEndpoint();

		if (endpoint != null) {
			if (proxiedUrl.contains("/content") || proxiedUrl.contains("/preferences")) {
				return proxiedUrl.replace("newsaustralia.api.mashery.com/proxy/content", endpoint + ".elasticbeanstalk.com/content");
			}
		}
		return proxiedUrl.replaceFirst("proxy/", "");
	}

	private String getEndpoint() {
		String endpoint = System.getProperty("ENDPOINT", System.getProperty("endpoint"));

		return endpoint == null ? null : endpoint.toLowerCase();
	}

	private void copyInputMethod(HttpServletRequest req, HttpURLConnection conn) throws ProtocolException {
		String method = req.getMethod();
		if (method.equals("POST") || method.equals("PUT")) {
			conn.setDoOutput(true);
		}
		conn.setRequestMethod(method);
		Logger.fine("Using method: " + method);
	}

	private void copyInputHeaders(HttpServletRequest req, HttpURLConnection conn) {
		Logger.fine("Copying request headers...");
		Enumeration headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = (String) headerNames.nextElement();
			if (IGNORE_HEADERS.contains(name)) {
				Logger.fine("Ignoring header: " + name);
				continue;
			}

			String value = req.getHeader(name);
			conn.setRequestProperty(name, value);
			Logger.fine("Copied header: " + name + "=" + value);
		}
	}

	private void copyInputBody(HttpServletRequest req, HttpURLConnection conn) throws IOException {
		if (conn.getDoOutput()) {
			// Pipe output directly from input stream into output stream
			Logger.fine("Piping request body...");

			OutputStream os = conn.getOutputStream();
			int copied = IOUtils.copy(req.getInputStream(), os);
			os.close();

			Logger.fine("Copied byte count" + copied);
		}
	}

	private HttpURLConnection getConnection(HttpServletRequest req) throws IOException {
		URL url = new URL(buildTargetUrl(req));
		Logger.info("Proxying to: " + url.toString());
		return (HttpURLConnection) url.openConnection();
	}

	private void sendResponse(HttpServletResponse res, int status, byte[] body) {
		sendResponse(res, status, null, body);
	}

	private void sendResponse(HttpServletResponse res, int status, Map<String, List<String>> headers, byte[] body) {
		res.setStatus(status);

		if (headers != null) {
			for (String name : headers.keySet()) {
				if (name == null) {
					continue;
				}

				String value = StringUtils.join(headers.get(name), ",");
				res.setHeader(name, value);
			}
		}

		if (body != null) {
			try {
				OutputStream os = res.getOutputStream();
				os.write(body);
				os.flush();
				os.close();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
	}

	private static class CustomizedHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
}
