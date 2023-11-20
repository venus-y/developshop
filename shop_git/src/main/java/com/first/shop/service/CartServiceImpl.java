package com.first.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.shop.dao.CartDao;
import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;

@Service
public class CartServiceImpl implements CartService {
	
	private final CartDao cartDao;
	
	public CartServiceImpl(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	// 장바구니 등록
	@Override
	public int registerCart(Cart cart) {
		// 총 적립포인트, 총 가격정보를 초기화해준다.
		cart.init();
		System.out.println("cart정보:"+cart);
		return cartDao.register(cart);
	}
	
	// 장바구니 상품 이미지 받아오기
	@Override
	public Product CartProductImage(int product_id) {
		return cartDao.productImage(product_id);
		}
	
	// 유저의 장바구니 목록 가져오기
	@Override
	public List<Cart> getCartList(String user_id) {
		return cartDao.list(user_id);
	}
	
	// 장바구니 정보 업데이트
	@Override
	public int updateCart(Cart cart) {
		// 장바구니 페이지에서 넘겨받은 cart객체의 정보로 기존 DB에 있던 장바구니의 정보를 다시 세팅한다.
		Cart dbCart = cartDao.cartInfo(cart.getCart_id());
		// 장바구니의 init 메서드로 가격, 총 적립포인트를 최신화한다.
		dbCart.setQuantity(cart.getQuantity());
		dbCart.init();		
		// 세팅된 값으로 갱신한다.
		return cartDao.update(dbCart);
	}
	
	// 장바구니 삭제
	@Override
	public int removeCart(Integer cart_id) {
		return cartDao.remove(cart_id);		
	}
	
	
	// 개별 장바구니 정보 가져오기
	@Override
	public Cart getCartInfo(int cart_id) {
		return cartDao.cartInfo(cart_id);
	}

	

}
