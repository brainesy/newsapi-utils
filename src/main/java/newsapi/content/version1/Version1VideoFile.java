package newsapi.content.version1;

import java.io.Serializable;
import java.math.BigDecimal;

public class Version1VideoFile implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer width;
	private Integer height;
	private String format;
	private String link;
	private Long fileSize;
	private Long bitrate;
	private BigDecimal duration;
	private BigDecimal frameRate;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getBitrate() {
		return bitrate;
	}

	public void setBitrate(Long bitrate) {
		this.bitrate = bitrate;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(BigDecimal frameRate) {
		this.frameRate = frameRate;
	}
}
