package com.first.shop.dto;

public class OrderInfoPageHandler {
	private int page;
	private int pageSize;
	private int totalCount;
	private int totalPageCount;
	private int navSize= 10;
	private int minPage = 1;
	private int maxPage;
	private int startPage;
	private int endPage;
	private boolean prevPage;
	private boolean nextPage;
	
	public OrderInfoPageHandler(Integer page, Integer pageSize, Integer totalCount) {
		this.totalCount = totalCount;
		if(page != null && pageSize != null) {
			this.page = page;
			this.pageSize = pageSize;
		}
		paging();
	}
	
	public void paging() {
		totalPageCount = (int)(Math.ceil(totalCount/(double)pageSize));
		maxPage = totalPageCount;
		startPage = (page-1)/navSize * navSize + 1;
		endPage = Math.min(maxPage, (page-1)+navSize);
		
		prevPage = (startPage == 1 ? false : true);
		
		nextPage = (endPage == maxPage ? false : true);
		
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
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getNavSize() {
		return navSize;
	}
	public void setNavSize(int navSize) {
		this.navSize = navSize;
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
		return "OrderInfoPageHandler [page=" + page + ", pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", totalPageCount=" + totalPageCount + ", navSize=" + navSize + ", minPage=" + minPage + ", maxPage="
				+ maxPage + ", startPage=" + startPage + ", endPage=" + endPage + ", prevPage=" + prevPage
				+ ", nextPage=" + nextPage + "]";
	}
	
	
}
