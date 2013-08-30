package newsapi.util.json;

import java.lang.reflect.Type;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonParser {
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
	private static final DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
	private static Gson gson;

	static {
		new JsonParser();
	}

	private JsonParser() {
		gson = new GsonBuilder()
		.registerTypeAdapter(DateTime.class, dateTimeSerializer)
		.registerTypeAdapter(DateTime.class, dateTimeDeserializer)
		.create();
	}

	public static <T> T fromJson(String json, Class<T> type)
	{
		return gson.fromJson(json, type);

	}

	@SuppressWarnings("unchecked")
	public static String toJson(Object object)
	{
		return gson.toJson(object);

	}

	JsonSerializer<Date> dateSerializer = new JsonSerializer<Date>() {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(src.getTime());
		}
	};

	JsonSerializer<DateTime> dateTimeSerializer = new JsonSerializer<DateTime>() {

		@Override
		public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(src.toString(dateTimeFormatter));
		}
	};

	JsonDeserializer<DateTime> dateTimeDeserializer = new JsonDeserializer<DateTime>() {
		@Override
		public DateTime deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return json == null ? null : DateTime.parse(json.getAsString(), dateTimeFormatter);
		}
	};

}
