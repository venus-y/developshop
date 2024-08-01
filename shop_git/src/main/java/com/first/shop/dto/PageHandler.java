package com.first.shop.dto;

public class PageHandler {
	
	private int totalCnt;
//	int page;
//	int pageSize;	
	private int navSize = 10;
	private int totalPageCnt;
	private int startPage;
	private int endPage;
	private int minPage = 1;
	private int maxPage;
	private boolean prevPage;
	private boolean nextPage;
	// 검색조건 
	private BrandSearchCondition bsc;
	
	
	
	//페이지값에 대한 정보가 없을 시 현재페이지 0으로 설정

	public BrandSearchCondition getBsc() {
		return bsc;
	}

	public void setBsc(BrandSearchCondition bsc) {
		this.bsc = bsc;
	}

	public PageHandler(int totalCount, BrandSearchCondition bsc) {
		this.totalCnt = totalCount;
		this.bsc = bsc;
		Paging(totalCount, bsc);
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

	
	public void Paging(int totalCount, BrandSearchCondition bsc) {
//		this.page = page;
//		this.pageSize = pageSize;
		this.totalCnt = totalCnt;
		
		// 게시물수/페이지사이즈로 페이지 개수를 정한다.
		totalPageCnt =   (int)(Math.ceil(((double)totalCnt/bsc.getPageSize())));
		// 페이지 개수가 딱 나누어떨어지지 않을 수 있다.
		// 예를 들어 총 게시물 수가 155개 페이지 사이즈가 10이라면 155/10 -> 15.5가 된다.
		// 이럴 경우 페이지 개수롤 16개로 만들어주어야 155번째 게시물까지 조회할 수 있다.
		 
		
		//시작페이지는 (현재 페이지-1)/navSize  * navSize + 1로 한다.
		startPage = ((bsc.getPage()-1)/navSize)*navSize + 1;
		
		

		//최대페이지는 최대페이지 cnt값으로 한다.
		this.maxPage = totalPageCnt;
		
		//끝페이지는 시작 페이지 - 1 * 페이지사이즈 + navSize로 한다.
		//최대페이지가 끝페이지 계산 결과보다 작을 경우 최대페이지를 끝페이지로 한다.		
		endPage =  Math.min(maxPage,startPage - 1 + navSize);  

		
		
		
		// 현재 페이지가 1과 같으면 이전으로 이동 불가, 현재 페이지가 최대페이지와 같다면 다음으로 이동 불가
		if(startPage == 1) {
			prevPage = false;
		}else {
			prevPage = true;
		}
		
		if(endPage == maxPage) {
			nextPage = false;
		}else {
			nextPage = true;
		}
	}
	
	
	

	@Override
	public String toString() {
		return "PageHandler [totalCnt=" + totalCnt + ", navSize=" + navSize + ", totalPageCnt=" + totalPageCnt
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", minPage=" + minPage + ", maxPage=" + maxPage
				+ ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", bsc=" + bsc + "]";
	}
	
	
	
	
}
