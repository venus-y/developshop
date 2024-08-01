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
		// 등록할 장바구니의 유저아이디, 상품 아이디가 이미 DB에 존재하는지 확인한다.
		int checkSame =  cartDao.same_product(cart);
		
		// 조회결과가 1이면 이미 존재하는 것이다
		if(checkSame == 1) {
			// 컨트롤러쪽으로 0을 반환 
			return 0;
		}
		
		// 총 적립포인트, 총 가격정보를 초기화해준다.
		cart.init();
		
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
		// 요청한 수량과 상품 재고를 비교하여 재고가 넉넉할 경우에만 업데이트 처리한다.
		int stock = cartDao.stock_int(cart.getProduct_id());
		
		if(cart.getQuantity() > stock) {
			return 0;
		}
		
		
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
	
	// 장바구니에 담긴 상품의 실재고 조회
	@Override
	public int getStock_Integer(Integer product_id) {
		return cartDao.stock_Integer(product_id);
	}

	@Override
	public int getStock_int(int product_id) {
		return cartDao.stock_int(product_id);
	}
	

	

}
