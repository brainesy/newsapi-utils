package newsapi.content;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import newsapi.content.version1.Version1Collection;
import newsapi.content.version1.Version1Content;
import newsapi.content.version1.Version1ContentSearchResult;
import newsapi.content.version1.Version1Image;
import newsapi.content.version1.Version1ImageGallery;
import newsapi.content.version1.Version1NewsStory;
import newsapi.content.version1.Version1Video;
import newsapi.util.TestUtil;
import newsapi.util.json.JsonParser;

import org.junit.Test;

public class ContentTest {
	@Test
	public void canConvertVersion1NewsStoryJsonToObject() {
		// Setup
		String jsonSearchResult = TestUtil.loadResource("/version1-news-story.json");

		// Exercise
		Version1Content content = JsonParser.fromJson(jsonSearchResult, Version1Content.class);

		// Assert
		assertThat(content, is(instanceOf(Version1NewsStory.class)));
		Version1NewsStory newsStory = (Version1NewsStory) content;
		assertThat(newsStory.getBody(), is(notNullValue()));
	}

	@Test
	public void canConvertVersion1SearchResponseJsonToObject() {
		// Setup
		String jsonSearchResult = TestUtil.loadResource("/version1-search-response.json");

		// Exercise
		Version1ContentSearchResult result = JsonParser.fromJson(jsonSearchResult, Version1ContentSearchResult.class);

		// Assert
		assertThat(result.getResultSize(), is(5));
		assertThat(result.getResults().get(0), is(instanceOf(Version1NewsStory.class)));
		assertThat(result.getResults().get(1), is(instanceOf(Version1Image.class)));
		assertThat(result.getResults().get(2), is(instanceOf(Version1Video.class)));
		assertThat(result.getResults().get(3), is(instanceOf(Version1ImageGallery.class)));
		assertThat(result.getResults().get(4), is(instanceOf(Version1Collection.class)));
	}

}
