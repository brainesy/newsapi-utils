package newsapi.util.json;

import java.lang.reflect.Type;

import newsapi.content.version1.Version1Collection;
import newsapi.content.version1.Version1Content;
import newsapi.content.version1.Version1ContentType;
import newsapi.content.version1.Version1Image;
import newsapi.content.version1.Version1ImageGallery;
import newsapi.content.version1.Version1NewsStory;
import newsapi.content.version1.Version1Video;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class Version1ContentAdapter implements  JsonDeserializer<Version1Content> {

	private static final ImmutableMap<Version1ContentType, Class<? extends Version1Content>> typeMap = new ImmutableMap.Builder<Version1ContentType, Class<? extends Version1Content>>()
			.put(Version1ContentType.NEWS_STORY, Version1NewsStory.class)
			.put(Version1ContentType.IMAGE, Version1Image.class)
			.put(Version1ContentType.VIDEO, Version1Video.class)
			.put(Version1ContentType.IMAGE_GALLERY, Version1ImageGallery.class)
			.put(Version1ContentType.COLLECTION, Version1Collection.class)
			.build();

	public Version1Content deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json == null) {
			return null;
		}

		String type = json.getAsJsonObject().get("contentType").getAsString();

		try {
			Version1ContentType contentType = Version1ContentType.valueOf(type);
			return context.deserialize(json, typeMap.get(contentType));
		} catch (Exception e) {
			throw new JsonParseException("Unknown element type: " + type, e);
		}
	}
}

