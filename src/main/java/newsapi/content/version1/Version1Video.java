package newsapi.content.version1;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class Version1Video extends Version1Content implements Serializable
{
	private List<Version1VideoFile> videoFiles = Lists.newArrayList();
	private List<Version1Image> images = Lists.newArrayList();

	private final static long serialVersionUID = 1L;

	public List<Version1VideoFile> getVideoFiles() {
		return videoFiles;
	}

	public List<Version1Image> getImages() {
		return images;
	}

}
