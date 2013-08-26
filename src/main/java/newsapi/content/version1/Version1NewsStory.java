package newsapi.content.version1;

import java.io.Serializable;

public class Version1NewsStory extends Version1Content implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String body;
	private String standFirst;
	private String kicker;
	private String byline;
	private Boolean commentsAllowed;
	private int commentsTotal;
	private Boolean commentsShown;

	public String getBody()
	{
		return body;
	}

	public void setBody( String body )
	{
		this.body = body;
	}

	public String getStandFirst()
	{
		return standFirst;
	}

	public void setStandFirst( String standFirst )
	{
		this.standFirst = standFirst;
	}

	public String getKicker()
	{
		return kicker;
	}

	public void setKicker( String kicker )
	{
		this.kicker = kicker;
	}

	public String getByline()
	{
		return byline;
	}

	public void setByline( String byline )
	{
		this.byline = byline;
	}

	public int getCommentsTotal()
	{
		return commentsTotal;
	}

	public void setCommentsTotal(int commentsTotal)
	{
		this.commentsTotal = commentsTotal;
	}

	public Boolean isCommentsShown()
	{
		return commentsShown;
	}

	public Boolean isCommentsAllowed() {
		return commentsAllowed;
	}

	public void setCommentsAllowed(Boolean commentsAllowed) {
		this.commentsAllowed = commentsAllowed;
	}

	public void setCommentsShown(Boolean commentsShown)
	{
		this.commentsShown = commentsShown;
	}

}
