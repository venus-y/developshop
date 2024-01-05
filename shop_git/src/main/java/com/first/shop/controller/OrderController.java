package com.first.shop.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.first.shop.dto.CartList;
import com.first.shop.dto.OrderInfoPageHandler;
import com.first.shop.dto.OrderProductList;
import com.first.shop.dto.OrderProductandCartList;
import com.first.shop.dto.OrdersList;
import com.first.shop.dto.Product;
import com.first.shop.dto.Purchase_History;
import com.first.shop.dto.TempOrderProduct;
import com.first.shop.dto.TempOrderProducts;
import com.first.shop.dto.User;
import com.first.shop.service.OrderService;
import com.first.shop.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	private final ProductService productService;
	
	private final String uploadPath;
	
	public OrderController(OrderService orderService, ProductService productService, String uploadPath) {
		this.orderService = orderService;
		this.productService = productService;
		this.uploadPath = uploadPath;
	}
	

	
	// 카카오페이 결제 필요한 정보
//	private String tid;
	
	
	
	// 장바구니 -> 주문페이지 -> 주문
	@PostMapping("/postOrder")
	public String order(OrdersList ordersList, OrderProductList orderProductList, CartList cartList,
			@RequestParam(required = false) boolean cartcheck, @RequestParam(defaultValue = "0", required = false) int used_point,
			BindingResult result, RedirectAttributes rattr) throws Exception{
		System.out.println("객체검증:" + result);
		System.out.println("장바구니에서 온 요청인지 확인:" + cartcheck);
		System.out.println("주문 정보 :" + ordersList);
		System.out.println("==================================================================================");
		System.out.println("주문 상품 :" + orderProductList);
		System.out.println("==================================================================================");
		System.out.println("삭제할 장바구니 정보:" + cartList);
		System.out.println("==================================================================================");
		System.out.println("사용 포인트 정보 : "+ used_point);
		
		try {
			int orderResult = orderService.registerOrders2(ordersList , orderProductList , cartList, used_point, cartcheck);
			
			if(orderResult != 1) {
				throw new Exception("Order failed");
			}else if(orderResult == 1) 
				// 잘 처리됐을 경우 주문처리가 잘 완료됐다는 메세지를 저장
				rattr.addFlashAttribute("msg", "ORDER_SUCCESS");
				return "redirect:/";	

		}catch(Exception e) {
			// 문제가 발생했을 경우 오류가 발생했단 메세지를 남긴다.
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "ORDER_FAILED");
			return "redirect:/";
		}

		
		
		
	}
	//주문화면
	@GetMapping("/getOrder/{user_id:.+}")
	public String getOrder(@PathVariable String user_id, TempOrderProducts tempOrderProducts,
			boolean cartCheck, BindingResult result , Model model) {
		
		if(cartCheck) {
			System.out.println("장바구니에서 온 요청");
			model.addAttribute("cartCheck", cartCheck);
		}else {
			System.out.println("즉시 구매요청");
		}
		// cartCheck가 true일 경우 model에 cartCheck 값을 추가
		
		
		System.out.println("값에 문제가 있나>"+result);
		System.out.println("받아온 값 테스트 : "+ tempOrderProducts);
		// 전달할 상품 정보를 리스트로 만들어 반환한다.
		List<TempOrderProduct> list = tempOrderProducts.getOrderProducts();
		
		// 장바구니에서 넘어온 데이터에는 총 합산가격과 총 적립포인트 정보가 세팅돼있지만 상품정보->즉시주문의 경우에는
		// 해당값들이 세팅돼있지 않다, 따라서 값을 세팅해준다. 즉시주문의 경우 한 개의 상품만 값을 세팅하면 된다.
		// 배열의 [0] 번째 요소가 합산가격, 합산포인트 정보가 없다면 즉시주문을 한 경우이니 검사 후 세팅
		
		if(list.get(0).getTotalprice() == 0 && list.get(0).getSaleprice() == 0 && list.get(0).getTotalsavepoint()==0) {
			list.get(0).setTotal();
			System.out.println(list.get(0));
		}
		
		// 주문한 유저 정보도 반환
		User user = orderService.getUserInfo(user_id);
		
		model.addAttribute("tempList", list);
		model.addAttribute("orderUser", user);
		return "/order/orderPage";
	}
	
	
	// 현재 사용 x
	// 상품정보 -> 주문화면
	@PostMapping("/fromInstantOrder/{user_id}")
	public String instantOrder(@PathVariable String user_id, TempOrderProducts tempOrderProducts, BindingResult result, Model model) {
//		System.out.println("전달값 출력:" + product_id + "," + id + "," + quantity);
//		orderService.registerOrders(product_id, id, quantity);
		System.out.println("오류 검출 결과:" + result);
		System.out.println("받아온 정보 출력:"+ tempOrderProducts);
		
		// 상품정보페이지에서 즉시주문을 누를 경우 하나의 상품정보만 주문페이지로 전달하면 된ㄷ.
		// InstantOrderProducts의 첫번째 요소에 총 합산가격과, 합산포인트 정보를 셋팅한다.
		for(TempOrderProduct product : tempOrderProducts.getOrderProducts()) {
			product.setTotal();
			// 셋팅 결과 출력
			System.out.println("합산가격, 합산포인트 셋팅 결과 출력:" + product);
		}
		
		return "redirect:/";

	}
	

	// 카카오페이
	@PostMapping("/kakaopay.cls")
	@ResponseBody
	public String kakaopay(@RequestBody OrderProductandCartList orderProductandCartList, HttpServletRequest request) {
		// 접근할 주소
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			// 서버에 연결해주는 클래스
			HttpURLConnection con = (HttpURLConnection) address.openConnection();
			// 통신방식
			con.setRequestMethod("POST");
			// 인증키
			con.setRequestProperty("Authorization", "KakaoAK 30ba730a4960c701c62ece47fbd8e4f5");
			// 요청 컨텐츠타입
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// 서버에 전송할 데이터가 있을 경우 true로 설정
			con.setDoOutput(true);
			
			// 넘겨줄 주문상품 및 장바구니 정보를 JSON 형태의 문자열로 변환
//			ObjectMapper objectMapper = new ObjectMapper();
//			String orderProductandCartListAsJson = objectMapper.writeValueAsString(orderProductandCartList);
//			
//			String approvalUrl = "http://localhost:8080/shop/order/kakaopay.approve";
//			String queryString = "?orderProductandCartList=" + URLEncoder.encode(orderProductandCartListAsJson, "UTF-8");
//			String fullUrl = approvalUrl + queryString;
			
			//결제시 표시될 항목명
			String item_name = "";
			if(orderProductandCartList.getOrderProductList().size() >= 2) {
				Product Product =  productService.getProductInfo(orderProductandCartList
						.getOrderProductList()
						.get(0)
						.getProduct_id());
				// 주문상품이 2개 이상일 경우 첫번째 상품 외 xx건
				item_name = Product.getProduct_name() + "외" + (orderProductandCartList.
						getOrderProductList().size() - 1) + "건";
			}else {
				// 주문상품이 한 개 일 경우 상품명만 표시
				Product Product =  productService.getProductInfo(orderProductandCartList
						.getOrderProductList()
						.get(0)
						.getProduct_id());
				item_name = Product.getProduct_name();
			}
			
			
			// 넘겨줄 매개변수
			String param = "cid=TC0ONETIME"
					+ "&partner_order_id=partner_order_id"
					+ "&partner_user_id=partner_user_id"
					+ "&item_name="+ URLEncoder.encode(item_name,"UTF-8")
					+ "&quantity=1&total_amount="+orderProductandCartList.getTotal_amount()
					+ "&tax_free_amount=0"
					+ "&approval_url=http://localhost:8080/shop/order/kakaopay.approve"
					+ "&cancel_url=http://localhost:8080/cancel"
					+ "&fail_url=http://localhost:8080/fail";
			
			System.out.println("param 출력: " + param);
			
			
			// 서버에 데이터를 전달해주는 클래스 
			OutputStream outputStream = con.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			// writeBytes() : 받은 문자열을 byte로 형변환해준다.
			dataOutputStream.writeBytes(param);
			// close() : 메서드 호출 시 자원을 닫음과 동시에 갖고 있던 데이터를 flush 처리
			dataOutputStream.close();
			
			// 받아올 응답코드
			int result = con.getResponseCode();
			// 데이터를 받아올 클래스
			InputStream inputStream;
			// 성공했을 경우
			if(result == 200) {
				inputStream = con.getInputStream();
			}else {
			// 실패했을 경우
				inputStream = con.getErrorStream();
			}
			// 받아온 데이터를 읽는 클래스
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			// 받아온 데이터를 byte -> String으로 형변환
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			// 읽은 문자열을 반환
			String readLine = bufferedReader.readLine();
			
			System.out.println(readLine);
			//tid 값 추출
			try {
				// tid 값을 orderProductandCartList에 저장
				orderProductandCartList.setTid(extractTid(readLine));				
				// 세션에 주문상품, 장바구니 목록 저장
				HttpSession session = request.getSession();
				session.setAttribute("orderProductandCartList", orderProductandCartList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			return readLine;
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	}
	
	
	  // 카카오페이 결제 승인	
	  @RequestMapping("/kakaopay.approve")
	  public String payComplete(@RequestParam("pg_token") String pgToken, Model model,
			  HttpServletRequest request,  RedirectAttributes rattr){
		  // 세션에 저장해뒀던 주문상품, 장바구니 목록을 가져온다.
		  HttpSession session = request.getSession();
		  
		  OrderProductandCartList orderProductandCartList = (OrderProductandCartList) session.getAttribute("orderProductandCartList"); 
		  System.out.println("주문상품: "+orderProductandCartList.getOrderProductList());
		  System.out.println("장바구니: "+orderProductandCartList.getCartList());
		  System.out.println("사용포인트: "+orderProductandCartList.getUsedPoint());
		  System.out.println("장바구니에서 온 요청 ?: " +orderProductandCartList.isCartCheck());

		  System.out.println(pgToken);
		  
		  
		  try {
			  URL address = new URL("https://kapi.kakao.com/v1/payment/approve");
			  // 서버에 연결해주는 클래스
			  HttpURLConnection con = (HttpURLConnection) address.openConnection();
			  // 통신방식
			  con.setRequestMethod("GET");
			  // 인증키
			  con.setRequestProperty("Authorization", "KakaoAK 30ba730a4960c701c62ece47fbd8e4f5");
			  // 요청 컨텐츠타입
			  con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			  // 서버에 전송할 데이터가 있을 경우 true로 설정
			  con.setDoOutput(true);
			  
			  System.out.println("tid 설정됐나: " + orderProductandCartList.getTid());
			  
			  
			// tid 변수에 큰따옴표가 포함되어 있다면 제거
			String tid = orderProductandCartList.getTid().replace("\"", "");
			  
			  // 넘겨줄 매개변수
			  String param = "cid=TC0ONETIME"
					+ "&tid="+ tid
					+ "&partner_order_id=partner_order_id"
					+ "&partner_user_id=partner_user_id"
					+ "&pg_token="+pgToken;
			  
			  System.out.println("param: " + param);
			  
			  
			  	// 서버에 데이터를 전달해주는 클래스
				OutputStream outputStream = con.getOutputStream();
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				// writeBytes() : 받은 문자열을 byte로 형변환해준다.
				dataOutputStream.writeBytes(param);
				// close() : 메서드 호출 시 자원을 닫음과 동시에 갖고 있던 데이터를 flush 처리
				dataOutputStream.close();
				
				// 받아올 응답코드
				int result = con.getResponseCode();
				// 데이터를 받아올 클래스
				InputStream inputStream;
				// 성공했을 경우
				if(result == 200) {
					inputStream = con.getInputStream();
					// 주문처리 
					orderService.kakaopay_OrderRegister(orderProductandCartList);
					// 세션에서 orderProductandCartList 삭제
					session.removeAttribute("orderProductandCartList");
					
					// 잘 처리됐을 경우 주문처리가 잘 완료됐다는 메세지를 저장
					rattr.addFlashAttribute("msg", "ORDER_SUCCESS");

				}else {
				// 실패했을 경우
					inputStream = con.getErrorStream();
				// 세션에서 orderProductandCartList 삭제
				session.removeAttribute("orderProductandCartList");
				// 실패했을 경우 실패했단 메세지를 저장
				rattr.addFlashAttribute("msg", "ORDER_FAILED");
			
				}
				// 받아온 데이터를 읽는 클래스
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				// 받아온 데이터를 byte -> String으로 형변환
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				// 읽은 문자열을 반환
				String readLine = bufferedReader.readLine();
				
				
				
				System.out.println(readLine);
				
		  } catch(Exception e) {
			  	e.printStackTrace();
		  }
		  		return "redirect:/";
	  }
	  
	  // tid(카카오페이 결제정보에 필요) 값을 추출하는 메서드
	  private String extractTid(String jsonData) { 
	  // "tid" 다음에 오는 큰 따옴표의 시작 인덱스
	  int start = jsonData.indexOf("\"tid\":\"") + 6;
    
	  // "tid" 다음에 오는 큰 따옴표의 끝 인덱스
	  int end = jsonData.indexOf("\"", start);

	  System.out.println("Start index: " + start);
	  // 직접 end 값을 찾아보기
	   int manualEnd = jsonData.indexOf("\"", start + 1); // start 이후에 나오는 다음 큰 따옴표 찾기
	   System.out.println("Manual End index: " + manualEnd);
	  System.out.println("Extracted tid: " + jsonData.substring(start,manualEnd+1));

	  
	  // start와 end 사이의 부분을 추출하여 반환
	  return (start >= 6 && end != -1) ? jsonData.substring(start, manualEnd+1) : null;
	  }
	  
	  // 유저 구매이력 페이지 
	  @GetMapping("/purchase_History")
	  public String purchase_History(String user_id, Integer page, Integer pageSize, Model model) {
		  
		  // 페이지와 페이지사이즈는 null일 경우 기본값을 세팅
		  if(page == null && pageSize == null) {
			  page = 1;
			  pageSize = 5;
		  }
		  
		  // 총 게시물 수를 가져온다.
		  int totalCount = orderService.get_Purchase_Count(user_id);
		  
		  // 페이징 처리
		  OrderInfoPageHandler ph = new OrderInfoPageHandler(page, pageSize, totalCount);
		  
		  // DB에서 페이징처리된 구매이력을 가져오는 데 필요한 offset, pageSize값을 Map에 담는다.
		  Map map = new HashMap();
		  map.put("offset", (ph.getPage()-1)*ph.getPageSize());
		  map.put("pageSize", ph.getPageSize());
		  map.put("user_id", user_id);
		  
		  // 구매이력 목록
		  List<Purchase_History> phList = orderService.get_Purchase_History(map);
		  
		  model.addAttribute("phList", phList);
		  model.addAttribute("ph",ph);
		  model.addAttribute("uploadPath", uploadPath);
		  
		  return "order/purchaseHistory";
		  
	  }
	  
	  
	
}
