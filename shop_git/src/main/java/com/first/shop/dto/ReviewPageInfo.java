package com.first.shop.dto;

import java.util.List;

public class ReviewPageInfo {
	List<Review> reviewList;
	
	ReviewPageHandler rph;

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public ReviewPageHandler getRph() {
		return rph;
	}

	public void setRph(ReviewPageHandler rph) {
		this.rph = rph;
	}

	@Override
	public String toString() {
		return "ReviewPage [reviewList=" + reviewList + ", rph=" + rph + "]";
	}
	
	
}
