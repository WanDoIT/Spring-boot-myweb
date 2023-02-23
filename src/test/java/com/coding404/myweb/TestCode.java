package com.coding404.myweb;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductMapper;
import com.coding404.myweb.util.Criteria;

@SpringBootTest
public class TestCode {

//	@Autowired
//	ProductMapper productMapper;
//	
//	@Test
//	public void testCode01() {
//		for(int i = 1; i <= 300; i++) {
//			
//	ProductVO vo = ProductVO.builder()
//					 .prod_enddate("2023-02-15")
//					 .prod_writer("admin")
//					 .prod_name("test"+ i)
//					 .prod_price(i *1000)
//					 .prod_count(i * 100)
//					 .prod_discount(i)
//					 .prod_purchase_yn("N")
//					 .prod_content("컨텐츠" + i)
//					 .prod_comment("I wanna go home")
//					 .build();
//		
//		productMapper.regist(vo);
//	}
		
	/*
	 * @Test public void testCode02() {
	 * 
	 * Criteria cri = new Criteria(1, 10);
	 * 
	 * ArrayList<ProductVO> list = productMapper.getList("admin", cri);
	 * System.out.println(list.toString()); }
	 */
		
	
}
