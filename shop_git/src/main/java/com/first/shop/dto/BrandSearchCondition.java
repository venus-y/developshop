package com.first.shop.dto;

import org.springframework.web.util.UriComponentsBuilder;

public class BrandSearchCondition {
	private Integer page = 1;
	private Integer pageSize = 8;
	private int category_code;
	private String keyword = "";
	
	public BrandSearchCondition(Integer page, Integer pageSize, String keyword, int category_code) {
		
		// page와 pageSize가 전달됐을 경우에만 담아준다.
		if(page != null && pageSize != null) {
			this.page = page;
			this.pageSize = pageSize;			
		}
		this.keyword = keyword;
		// 브랜드의 경우 카테고리 코드의 첫번째 자리만 필요하므로 / 100으로 앞에 한 자리만 남긴 값을 저장.
//		this.category_code = category_code/100 ;
	}
	
	//  검색조건 정보 생성 메서드
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
					.queryParam("page", page)
					.queryParam("pageSize", pageSize)
					.queryParam("category_code", category_code)
					.queryParam("keyword", keyword)
					.build().toString();
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}
	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getOffset() {
		return (page-1)*pageSize;
	}
	
	@Override
	public String toString() {
		return "BrandSearchCondition [page=" + page + ", pageSize=" + pageSize + ", category_code=" + category_code
				+ ", keyword=" + keyword + "]";
	}

	
}
