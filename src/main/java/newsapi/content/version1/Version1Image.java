package newsapi.content.version1;

import java.io.Serializable;

public class Version1Image extends Version1Content implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String format;
	private int width;
	private int height;
	private String imageName;
	private Version1ImageType imageType;

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public String getImageName()
	{
		return imageName;
	}

	public void setImageName(String imageName)
	{
		this.imageName = imageName;
	}

	public Version1ImageType getImageType() {
		return imageType;
	}

	public void setImageType(Version1ImageType imageType) {
		this.imageType = imageType;
	}
}
