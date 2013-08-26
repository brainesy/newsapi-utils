package newsapi.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import newsapi.util.json.JsonParser;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;

public class TestUtil {

	public static String loadResource(String resourceName) {
		InputStream stream = TestUtil.class.getResourceAsStream(resourceName);
		if (stream == null) {
			throw new IllegalArgumentException("Resource not found on classpath: " + resourceName);
		}
		try {
			return IOUtils.toString(stream, Charsets.UTF_8.name());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T loadFromJsonResource(String stringJsonResource, Class<T> clazz) {
		String jsonObject = loadResource(stringJsonResource);

		return JsonParser.fromJson(jsonObject, clazz);
	}

	public static String getUrl(String urlString) {
		String data;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(5 * 1000);
			connection.setReadTimeout(15 * 1000);
			connection.setRequestProperty("Content-Encoding", "UTF-8");

			connection.connect();

			data = IOUtils.toString(connection.getInputStream(), "UTF-8");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return data.toString();
	}
}
