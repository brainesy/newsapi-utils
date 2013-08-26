package newsapi.content.version1;

import java.util.List;

import org.joda.time.DateTime;

public class Version1Content {
	protected String id;

	protected String title;
	protected String subtitle;
	protected String description;
	protected String link;
	protected String paidStatus;
	protected String originalSource;
	protected String creditedSource;
	protected String subscriptionSummary;
	protected DateTime dateUpdated;
	protected Version1ContentType contentType;
	protected Version1ContentStatus status;
	protected Version1ReferenceType referenceType;
	protected Version1Image thumbnailImage;
	protected List<Version1Content> related;
	protected List<String> categories;
	protected List<String> keywords;
	protected List<String> authors;
	protected List<String> domains;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Version1ContentType getContentType() {
		return contentType;
	}

	public void setContentType(Version1ContentType contentType) {
		this.contentType = contentType;
	}

	public Version1ReferenceType getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(Version1ReferenceType referenceType) {
		this.referenceType = referenceType;
	}

	public DateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(DateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public String getOriginalSource() {
		return originalSource;
	}

	public void setOriginalSource(String originalSource) {
		this.originalSource = originalSource;
	}

	public String getCreditedSource() {
		return creditedSource;
	}

	public void setCreditedSource(String creditedSource) {
		this.creditedSource = creditedSource;
	}

	public String getSubscriptionSummary() {
		return subscriptionSummary;
	}

	public void setSubscriptionSummary(String subscriptionSummary) {
		this.subscriptionSummary = subscriptionSummary;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Version1ContentStatus getStatus() {
		return status;
	}

	public void setStatus(Version1ContentStatus status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Version1Image getThumbnailImage()
	{
		return thumbnailImage;
	}

	public void setThumbnailImage(Version1Image thumbnailImage)
	{
		this.thumbnailImage = thumbnailImage;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public List<Version1Content> getRelated() {
		return related;
	}

	public void setRelated(List<Version1Content> related) {
		this.related = related;
	}

}