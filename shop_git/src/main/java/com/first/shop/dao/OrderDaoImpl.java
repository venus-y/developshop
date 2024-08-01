package com.first.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Cart;
import com.first.shop.dto.DeliveryInfo;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.Purchase_History;
import com.first.shop.dto.User;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	String namespace="com.first.shop.dao.OrderMapper.";	
	
	@Autowired
	SqlSession session;
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Product> productByIds(List<Integer> productIds) {
		return session.selectList(namespace+"selectProductsByIds", productIds);
	}
	
	@Override
	public int register(Orders orders) {
		return session.insert(namespace+"registerOrder", orders); 
	}
	
	@Override
	public int order_product(OrderProduct orderProduct) {
		return session.insert(namespace+"registerOrderproduct", orderProduct);
	}
	
	@Override
	public int updateUser(User user) {
		return session.update(namespace+"updateUser", user);
	}
	
	@Override
	public User user(String id) {
		return session.selectOne(namespace+"orderUserInfo", id);
	}

	@Override
	public Product product(int product_id) {
		return session.selectOne(namespace+"orderProductInfo", product_id);		
	}
	
	@Override
	public int delete(Cart cart) {
		return session.delete(namespace+"deleteOrderedCart", cart);
	}
	
	@Override
	public int delivery_info(DeliveryInfo deliveryInfo) {
		return session.insert(namespace+"register_delivery_info", deliveryInfo);
	}
	
	// 카카오페이 주문정보 등록
	@Override
	public int kakaopay_order(Orders orders) {
		return session.insert(namespace+"registerOrder", orders);
	}
	
	@Override
	public int kakaopay_orderproduct(OrderProduct orderProduct) {
		return session.insert(namespace+"registerOrderproduct", orderProduct);
	}

	@Override
	public List<Purchase_History> purchase_History(Map map) {
		return session.selectList(namespace+"getPurchaseHistory", map);
	}

	@Override
	public int purchase_Count(String user_id) {
		return session.selectOne(namespace+"getPurchaseCount", user_id);
	}
	
    @Override
    public int deleteCart(List<Cart> carts) {
        try (SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(org.apache.ibatis.session.ExecutorType.BATCH)) {
            for (Cart cart : carts) {
                session.delete(namespace + "deleteOrderedCart", cart);
            }
            session.commit();
        }
        return carts.size();
    }

    @Override
    public int registerOrder(List<Orders> orderList) {
        try (SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(org.apache.ibatis.session.ExecutorType.BATCH)) {
            for (Orders order : orderList) {
                session.insert(namespace + "registerOrder", order);
            }
            session.commit();
        }
        return orderList.size();
    }

    @Override
    public int registerOrderProduct(List<OrderProduct> orderProducts) {
        try (SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(org.apache.ibatis.session.ExecutorType.BATCH)) {
            for (OrderProduct orderProduct : orderProducts) {
                session.insert(namespace + "registerOrderproduct", orderProduct);
            }
            session.commit();
        }
        return orderProducts.size();
    }

    @Override
    public int updateStock(List<Product> products) {
        try (SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(org.apache.ibatis.session.ExecutorType.BATCH)) {
            for (Product product : products) {
                session.update(namespace + "updateStock", product);
            }
            session.commit();
        }
        return products.size();
    }
	


	

	

	

}
