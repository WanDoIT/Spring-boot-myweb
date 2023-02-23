package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	

	@GetMapping("/productList")
	public String list(HttpSession session,
						Model model,
						Criteria cri) {/* HttpServletRequest request */
		
		/*
		 1. 검색폼에서는 키워드, page, amount 데이터를 넘긴다
		 2. 목록 조회 and total 동적쿼리로 변경
		 3. 페이지네이션에 키워드, page, amount 데이터를 넘긴다
		 */
		
		
		//프로세스 
		//admin이라고 가정(사용할 값이 현재 없기 때문에)
//		session.setAttribute("user_id", "admin");
		
		//로그인한 회원만 조회
		System.out.println(cri.toString());
		
		String user_id = (String)session.getAttribute("user_id");
		ArrayList<ProductVO> list = productService.getList(user_id, cri);
		model.addAttribute("list", list);
		
		
		
		
		//페이지네이션 처리
		int total = productService.getTotal(user_id, cri);
		PageVO pageVO = new PageVO(cri, total);
		
		model.addAttribute("pageVO", pageVO);
		
		return "product/productList";
	}
	
	//상세페이지로
	@GetMapping("/productDetail")
	public String detail() {
		return "product/productDetail";
	}
	

	//등록화면으로
	@GetMapping("/productReg")
	public String reg() {
		return "product/productReg";
	}
	
	
	
	//등록 버튼 누르면 넘어가는 화면 
	@PostMapping("/registForm")
	public String registForm(/*Valid*/ ProductVO vo,
							RedirectAttributes ra,
							@RequestParam("file") List<MultipartFile> list) {
		//파일업로드 작업 =>
		//리스트에서 빈값은 제거
		list = list.stream()
					.filter((x) -> x.isEmpty() == false)
					.collect(Collectors.toList());
		
		//확장자가 image라면 경고문
		for(MultipartFile file : list) {
			if(file.getContentType().contains("image") ==false) {
				ra.addFlashAttribute("msg","png, jpg, jpeg형식만 들록이 가능합니다");
				return "redirect:/product/productReg";
			}
			
		}
		
		
		
		//파일 업로드 작억을 -> service영역으로 위임
		
		//글등록
		int result = productService.regist(vo,list);
		System.out.println(result);
		String msg = result == 1 ? "정상적으로 입력되었습니다" : "등록에 실패하였습니다";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/product/productList"; //목록화면으로
		
	}
	
	
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/xxx") public String xxx() {
	 * 
	 * return "경로"; }
	 */
	
	
}
