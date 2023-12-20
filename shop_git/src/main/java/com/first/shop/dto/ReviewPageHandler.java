package com.first.shop.dto;

public class ReviewPageHandler {
	private int page;
	private int pageSize;
	private int navSize = 5;
	private int startPage;
	private int totalCnt;
	private int totalPageCnt;
	private int endPage;
	private int minPage = 1;
	private int maxPage;
	private boolean prevPage;
	private boolean nextPage;
	
	
	public ReviewPageHandler(int totalCnt, int page, int pageSize) {
		paging(totalCnt, page, pageSize);
	}
	
	// 페이징 메서드
	public void paging(int totalCnt, int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
		this.totalCnt = totalCnt;
		
		// 총 페이지 수 -> 전체 리뷰 수를 페이지사이즈로 나눈 값을 채택 -> 소수점으로 나올 경우 그보다 하나 큰 정수로 처리
		totalPageCnt =  (int)(Math.ceil(totalCnt/(double)pageSize));
		
		// 최대페이지 -> 총 페이지
		maxPage = totalPageCnt;
		
		// 시작페이지 -> (현재페이지 / navSize) * navSize + 1 
		startPage = ((page-1)/ navSize) * navSize + 1;
		// 끝페이지 -> (현재페이지/ navSize) * navSize -> 최대페이지와 비교해서 더 작은 쪽을 끝 페이지로 지정 
		endPage = Math.min(((page-4)/navSize) * navSize + navSize, maxPage);
		
		// 시작페이지가 최소페이지와 같다면 이전으로 갈 수 없고, 끝페이지가 최대페이지와 같다면 다음으로 갈 수 없다.
		prevPage = startPage == minPage ? false : true;
		
		nextPage = endPage == maxPage ? false : true;
	
	}
	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
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
		return "ReviewPageHandler [page=" + page + ", pageSize=" + pageSize + ", navSize=" + navSize + ", startPage="
				+ startPage + ", totalCnt=" + totalCnt + ", totalPageCnt=" + totalPageCnt + ", endPage=" + endPage
				+ ", minPage=" + minPage + ", maxPage=" + maxPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage
				+ "]";
	}
	
	
}
