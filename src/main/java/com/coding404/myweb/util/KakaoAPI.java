package com.coding404.myweb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("kakao")
public class KakaoAPI {

	//토큰 발급 기능
	public String getAccessToken(String code) {
		
		String requestURL = "https://kauth.kakao.com/oauth/token";
		String redirect_url="http://127.0.0.1:8484/user/kakao";
		
		String access_token="";
		String refresh_token = "";
		
		//post의 폼데이터 형식 키=값&키=값....
		String data = "grant_type=authorization_code"
						+"&client_id=04927229693df181885b56e2db70787a"
						+"&redirect_uri="+redirect_url
						+"&code="+code;
		
		try {
			URL url = new URL(requestURL);
			
			HttpURLConnection conn= (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST"); //post형식
			conn.setDoOutput(true); //카카오서버로부터 데이터 응답을 허용
			//데이터 전송을 위한 클래스
//			OutputStream out = conn.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(out);
//			BufferedWriter br = new BufferedWriter(osw);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(data);
			bw.flush();
			//응답 결과를 conn객체에서 받음
			System.out.println("요청결과:"+conn.getResponseCode()); 
			if(conn.getResponseCode()==200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String result = "";
				String str = null;
				while( (str = br.readLine()) != null) { //한줄씩 읽음 - 읽을 값이 없다면 null반환
					result += str;
				}
				
				System.out.println(result);
				
				//제이슨 데이터 분해
				JsonParser json = new JsonParser(); //com.google.~~
				
				JsonElement element = json.parse(result); //json데이터 전달
				JsonObject obj = element.getAsJsonObject();
				access_token=obj.get("access_token").getAsString();
				refresh_token = obj.get("refresh_token").getAsString();
				
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return access_token;
	}
	//토큰기반으로 유저정보 얻기
	public Map<String, Object> getUserInfo(String access_token){
		String requestURL = "https://kapi.kakao.com/v2/user/me";
		//데이터 저장할 map
		Map<String, Object> map = new HashMap<>();
		
		try {
			URL url = new URL(requestURL);
			
			HttpURLConnection conn= (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST"); //post형식
			conn.setDoOutput(true); //카카오서버로부터 데이터 응답을 허용
			
			//헤더에 토큰정보를 추가
			conn.setRequestProperty("Authorization", "Bearer "+access_token);
			
			System.out.println("토큰요청결과:"+conn.getResponseCode() );
			
			if(conn.getResponseCode()==200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String result = "";
				String str = null;
				while( (str = br.readLine()) != null) { //한줄씩 읽음 - 읽을 값이 없다면 null반환
					result += str;
				}
			System.out.println(result);
			
	        JsonParser json = new JsonParser();
	        JsonElement element = json.parse(result);
	        
	        //json에서 key를 꺼내고, 다시 key를 꺼낸다.
	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	        
	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	        String email = kakao_account.getAsJsonObject().get("email").getAsString();
	        
	        map.put("nickname", nickname);
	        map.put("email", email);
	        
			
		}
			} catch (Exception e) {
			// TODO: handle exception
		}
		return map;
	}
	
	
}
