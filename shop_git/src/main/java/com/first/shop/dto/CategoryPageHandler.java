package com.first.shop.dto;

public class CategoryPageHandler {
	private int navSize = 10;
	private int totalCnt;
	private int totalPageCnt;
	private int startPage;
	private int endPage;
	private int minPage = 1;
	private int maxPage;
	private boolean prevPage;
	private boolean nextPage;
	private CategorySearchCondition csc;

	public CategoryPageHandler (int totalCnt, CategorySearchCondition csc) {
		this.totalCnt = totalCnt;
		this.csc = csc;
		categoryPaging(totalCnt, csc);
	}
	
	public void categoryPaging(int totalCnt, CategorySearchCondition csc) {
		totalPageCnt = (int)(Math.ceil(((double)totalCnt)/csc.getPageSize()));
	
		maxPage = totalPageCnt;

		startPage = (csc.getPage()/navSize)+1;
		
		endPage = Math.min(maxPage ,(csc.getPage()/navSize)+navSize);
		
		prevPage = (startPage == 1 ? false : true);
		
		nextPage = (csc.getPage() == maxPage ? false : true);
			
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

	public CategorySearchCondition getCsc() {
		return csc;
	}

	public void setCsc(CategorySearchCondition csc) {
		this.csc = csc;
	}

	@Override
	public String toString() {
		return "CategoryPageHandler [navSize=" + navSize + ", totalCnt=" + totalCnt + ", totalPageCnt=" + totalPageCnt
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", minPage=" + minPage + ", maxPage=" + maxPage
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", csc=" + csc + "]";
	}
	
	
	
}
