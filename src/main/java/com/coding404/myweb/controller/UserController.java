package com.coding404.myweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding404.myweb.util.KakaoAPI;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private KakaoAPI kakao;
	
	@GetMapping("/join")
	public String join() {
		
		return "user/join";
		
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@GetMapping("/userDetail")
	public String userDetail() {
		
		return "user/userDetail";
	}
	
	//카카오 redirect_uri
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code) {
		System.out.println("인가코드:"+code);
		
		String token = kakao.getAccessToken(code);
		System.out.println("어세스토큰:"+token);
		
		Map<String, Object> map = kakao.getUserInfo(token);
		
		System.out.println("사용자 데이터:"+map.toString());
		
		return "redirect:/main";
	}
	
}
