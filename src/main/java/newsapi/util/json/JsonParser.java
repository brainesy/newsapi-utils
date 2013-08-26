package newsapi.util.json;

import java.lang.reflect.Type;

import newsapi.content.version1.Version1Content;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class JsonParser {
	private static final DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
	private static Gson gson;

	static {
		new JsonParser();
	}

	private JsonParser() {
		gson = new GsonBuilder()
		.registerTypeAdapter(DateTime.class, dateTimeDeserializer)
		.registerTypeAdapter(Version1Content.class, new Version1ContentAdapter())
		.create();
	}

	public static <T> T fromJson(String json, Class<T> type)
	{
		return gson.fromJson(json, type);
	}

	public static String toJson(Object object)
	{
		return gson.toJson(object);
	}

	JsonDeserializer<DateTime> dateTimeDeserializer = new JsonDeserializer<DateTime>() {
		public DateTime deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return json == null ? null : DateTime.parse(json.getAsString(), dateTimeFormatter);
		}
	};

}
