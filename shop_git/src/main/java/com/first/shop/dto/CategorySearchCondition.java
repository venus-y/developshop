package com.first.shop.dto;

import org.springframework.web.util.UriComponentsBuilder;

public class CategorySearchCondition {
	private Integer page = 1;
	private Integer pageSize = 8;
	private int category_code;
	private String viewName = "";
	private String detail = "";
	private String keyword = "";
	
	public CategorySearchCondition(Integer page, Integer pageSize,
			String keyword, int category_code, String viewName, String detail) {
		
		
		
		
		if(page != null && pageSize != null) {
			this.page = page;
			this.pageSize = pageSize;
		}
		if(keyword != null) {
			this.keyword = keyword;			
		}
		this.category_code = category_code;
		this.viewName = viewName;
		if(detail != null && detail != "") {
			this.detail = detail;			
		}
	}
	
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("category_code", category_code)
				.queryParam("keyword", keyword)
				.queryParam("detail", detail)
				.queryParam("viewName", viewName)
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
	
	public int getOffset() {
		return (page-1) * pageSize;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "CategorySearchCondition [page=" + page + ", pageSize=" + pageSize + ", category_code=" + category_code
				+ ", viewName=" + viewName + ", detail=" + detail + ", keyword=" + keyword + "]";
	}

	
	
	
}
