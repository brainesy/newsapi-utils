package newsapi.content.version1;

import java.util.List;

import com.google.common.collect.Lists;

public class Version1ContentSearchResult {

	private long totalHits;
	private int offset;
	private int pageSize;
	private int resultSize;
	private List<Version1Content> results = Lists.newArrayList();

	private void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public int getResultSize() {
		return resultSize;
	}

	public long getTotalHits() {
		return totalHits;
	}

	public void setTotalHits(long totalHits) {
		this.totalHits = totalHits;
	}
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Version1Content> getResults() {
		return results;
	}
}
