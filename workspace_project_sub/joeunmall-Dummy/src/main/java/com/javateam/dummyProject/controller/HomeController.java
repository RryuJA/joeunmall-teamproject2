package com.javateam.dummyProject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javateam.dummyProject.service.UserService;
import com.javateam.dummyProject.dao.ProductDAO;
import com.javateam.dummyProject.dao.UserDAO;
import com.javateam.dummyProject.domain.ProductVO;
import com.javateam.dummyProject.domain.UserVO;
import com.javateam.dummyProject.service.InquiryService;
import com.javateam.dummyProject.service.OrderService;
import com.javateam.dummyProject.service.ProductImageService;
import com.javateam.dummyProject.service.ProductOptionService;
import com.javateam.dummyProject.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	ProductService productSvc;
	//InquiryService inquirySvc;
	//UserService userSvc;
	//ProductOptionService productOptionSvc;
	//ProductImageService productImageSvc;
	//OrderService orderSvc;
	
	@Autowired
	ProductDAO productDAO;
	//UserDAO userDAO;
	
	
	@GetMapping("/")
	public String home(Model model) throws ClassNotFoundException, IOException {
		
		//log.info("home");
		
		//직렬화
		//userSvc.dummyData();
		//productSvc.crawlProducts();
		//productImageSvc.dummyData();
		//productOptionSvc.dummyData();
		//inquirySvc.dummyData();
		
		//역직렬화
		//model.addAttribute("userList", userSvc.getUserListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\UserListDummy.ser"));
		//model.addAttribute("prList", productSvc.getProductListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\ProductListDummy.ser"));
		//model.addAttribute("prImageList", productImageSvc.getProductImageListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\ProductImageListDummy.ser"));
		//model.addAttribute("prOptionList", productOptionSvc.getProductOptionListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\ProductOptionListDummy.ser"));
		
		//더미데이터 삽입
		//UserVO
		/*List<UserVO> userList = userSvc.getUserListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\UserListDummy.ser"); 
		
		for(int i=0; i<userList.size(); i++) {
			userDAO.insertUserTBL(userList.get(i));
		}*/
		
		//ProductVO
		List<ProductVO> productList = productSvc.getProductListFromSerFile("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser\\ProductListDummy.ser");
		
		for(int i=0; i<productList.size(); i++) {
			productDAO.insertProductTBL(productList.get(i));
		}
		
		return "home"; 
	}

}