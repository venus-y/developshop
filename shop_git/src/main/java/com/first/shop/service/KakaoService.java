package com.first.shop.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.first.shop.dao.KakaoDao;
import com.first.shop.dto.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoService {
	
	private final KakaoDao kakaoDao;
	
	public KakaoService (KakaoDao kakaoDao) {
		this.kakaoDao = kakaoDao;
	}
	
	public String getReturnAccessToken(String code) {
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
	
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=89d603397791d99836c710255262fc14");
			sb.append("&redirect_uri=http://localhost:8080/shop/kakao/kakao_callback");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String br_line = "";
			String result = "";
					
			while((br_line = br.readLine()) != null) {
				result += br_line;
			}
					
			JsonParser parser = new JsonParser(); 
			JsonElement element = parser.parse(result);
			
			access_token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();
		
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return access_token;
	}
	
	public Map<String,Object> getUserInfo(String access_token){
		Map<String,Object> resultMap = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		 try {
			 URL url = new URL(reqURL);
			 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			 conn.setRequestMethod("GET");
			 
			 conn.setRequestProperty("Authorization", "Bearer " + access_token);
			 
			 int responseCode = conn.getResponseCode();
			 
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		  
			 String br_line = "";
			 String result = "";
			 
			 while((br_line = br.readLine()) != null) {
				 result += br_line;
			 }
			 
			 
			 
			 JsonParser parser = new JsonParser();
			 JsonElement element = parser.parse(result);
			 
			 JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			 JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			 
			 String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			 String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
			 String email = kakao_account.getAsJsonObject().get("email").getAsString();
			 resultMap.put("nickname", nickname);
			 resultMap.put("profile_image", profile_image);
			 resultMap.put("email", email);
			 
		 } catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 return resultMap;
	}
	
		public Map<String,Object> getAddress(String access_token){
			Map<String,Object> resultMap = new HashMap<>();
			String reqURL = "https://kapi.kakao.com/v1/user/shipping_address";
			 try {
				 URL url = new URL(reqURL);
				 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				 conn.setRequestMethod("GET");
				 
				 conn.setRequestProperty("Authorization", "Bearer " + access_token);
				 
				 int responseCode = conn.getResponseCode();
				 
				 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			  
				 String br_line = "";
				 String result = "";
				 
				 while((br_line = br.readLine()) != null) {
					 result += br_line;
				 }
				 
				 
				 JsonParser parser = new JsonParser();
				 JsonElement element = parser.parse(result);
				 
				 JsonArray addresses = element.getAsJsonObject().get("shipping_addresses").getAsJsonArray();
				 
				 for(JsonElement addressElement : addresses) {
					 JsonObject addressObject = addressElement.getAsJsonObject();
					 String baseAddress = addressObject.get("base_address").getAsString();
					 String detailAddress = addressObject.get("detail_address").getAsString();
					 String receiverName = addressObject.get("receiver_name").getAsString();
					 String receiverPhoneNumber = addressObject.get("receiver_phone_number1").getAsString();

					 resultMap.put("baseAddress", baseAddress);
					 resultMap.put("detailAddress", detailAddress);
					 resultMap.put("receiverName", receiverName);
					 resultMap.put("receiverPhoneNumber", receiverPhoneNumber);

 
				 }
				 
				
				 
			 } catch(IOException e) {
				 e.printStackTrace();
			 }
			 
			 return resultMap;
		}
		
		public User getKaKaoUser_Info(String id) {
			return kakaoDao.kakaoUser(id);
		}
		
		
}
