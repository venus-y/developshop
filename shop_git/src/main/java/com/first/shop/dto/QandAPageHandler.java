package com.first.shop.dto;

public class QandAPageHandler {
	private int page;
	private	int pageSize;
	private	int navSize = 10;
	private int totalCnt;
	private int totalPageCnt;
	private int startPage;
	private int endPage;
	private int minPage = 1;
	private int maxPage;
	private boolean prevPage;
	private	boolean nextPage;
	
	public QandAPageHandler(Integer page, Integer pageSize, Integer totalCnt) {
		paging(page, pageSize, totalCnt);
	}
	
	public void paging(Integer page, Integer pageSize, Integer totalCnt) {
		this.page = page;
		this.pageSize = pageSize;
		this.totalCnt = totalCnt;
		// 총 페이지 수를 지정해준다.
		totalPageCnt = (int)Math.ceil(totalCnt/(double)navSize);
		// 최대 페이지는 총 페이지 카운트의 값으로 설정
		maxPage = totalPageCnt;
		// 시작 페이지는 (현재 페이지 - 1 )/navSize * navSize + 1
		startPage = (this.page - 1)/navSize * navSize + 1;
		// 끝 페이지는 (시작페이지 - 1 + navSize)와 maxPage중 더 작은 값을 택한다.
		endPage = Math.min(maxPage, (startPage - 1 + navSize));
		// 시작페이지가 1, 끝페이지가 최대페이지와 같을 경우 이전, 다음으로 이동 불가
		prevPage = startPage == minPage ? false : true;
		nextPage = endPage == maxPage ? false : true;
	}
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getNavSize() {
		return navSize;
	}
	public void setNavSize(int navSize) {
		this.navSize = navSize;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getTotalPageCnt() {
		return totalPageCnt;
	}
	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getMinPage() {
		return minPage;
	}
	public void setMinPage(int minPage) {
		this.minPage = minPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public boolean isPrevPage() {
		return prevPage;
	}
	public void setPrevPage(boolean prevPage) {
		this.prevPage = prevPage;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	@Override
	public String toString() {
		return "QandAPageHandler [page=" + page + ", pageSize=" + pageSize + ", navSize=" + navSize + ", totalCnt="
				+ totalCnt + ", totalPageCnt=" + totalPageCnt + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", minPage=" + minPage + ", maxPage=" + maxPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ "]";
	}
	
	
	
}
