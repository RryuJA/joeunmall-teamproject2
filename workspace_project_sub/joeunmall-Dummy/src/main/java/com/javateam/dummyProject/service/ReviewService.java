package com.javateam.dummyProject.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.javateam.dummyProject.domain.OrderProductVO;
import com.javateam.dummyProject.domain.OrderVO;
import com.javateam.dummyProject.domain.ProductVO;
import com.javateam.dummyProject.domain.ReviewVO;
import com.javateam.dummyProject.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author RJA
 */

@Service
@Slf4j
public class ReviewService {
	
	/** PRODUCT_TBL에 저장된 상품정보의 상품번호만 저장 */
	private Map<String, Integer> productReviewCount = new HashMap<String, Integer>();
	
	/** ORDER_TBL에 저장된 주문정보 저장 */
	private List<OrderVO> orderDummy;
	
	/** ORDER_PRODUCT_TBL에 저장된 주문상품정보 중 첫번째 옵션만 저장 */
	private Map<String, OrderProductVO> orderOptionMap = new HashMap<String, OrderProductVO>();
	
	/**
	 * ProductDAO.selectProductTBLALL() 결과를 가져옴
	 * @param productDummy
	 */
	public void setProductDummy(List<ProductVO> productDummy) {		
		//Map<상품번호, 상품당 리뷰수>
		for(int i=0; i<productDummy.size(); i++) {
			productReviewCount.put(productDummy.get(i).getProductIndex(), 0);
		}
	}
	
	/**
	 * OrderDAO.selectOrderTBLAll() 결과를 가져옴
	 * @param orderDummy
	 */
	public void setOrderDummy(List<OrderVO> orderDummy) {
		this.orderDummy = orderDummy;
	}
	
	/**
	 * OrderProductDAO.selectOrderProductTBLAll() 결과를 가져옴
	 * @param orderProductDummy
	 */
	public void setOrderProductDummy(List<OrderProductVO> orderProductDummy) {		
		for(int i=0; i<orderDummy.size(); i++) {
			orderOptionMap.put(orderDummy.get(i).getOrderIndex(), orderProductDummy.get(i));
		}
	}
	
	/**
	 * 리뷰번호 형식 : ﻿00_00_000_REV000
	 * ﻿﻿'상품번호' (+) _REV (+) '해당 상품의 리뷰 등록 순서 3자리'
	 * @param 상품번호
	 * @return 리뷰번호
	 */
	private String makeReviewIndex(String productIndex) {
		String result = productIndex + "_REV";
		
		int count = productReviewCount.get(productIndex) + 1;
		productReviewCount.put(productIndex, count);
		
		result += String.format("%03d", productReviewCount.get(productIndex));
		
		return result;
	}

	private String makeReviewContent() {
		String contents[] = {
				"아주 편해요",
				"스타일 좋아서 구매했는데 다들 이쁘다고해요",
				"막 입기 아까울정도로 이쁘고 넘 맘에드네요~",
				"다들 어디에서 샀냐고 물어보네요",
				"아침 저녁으로 입기 딱좋고~~ 늘 입어도 될거같아요^^",
				"단순하게 입어도 요거 입어주면 밋밋하지 않고 느낌있게 코디가능",
				"좀 작아요",
				"신축성이 아주 좋네요 넉넉히 입고 싶어요. 생각보다 두꺼워요",
				"요새 매일 입고 다녀요"
		};
		
		String result = contents[(int)(Math.random() * contents.length)];
		return result;
	}
	
	// 더미데이터 리스트
	public void dummyData() {
		List<ReviewVO> reviewList = new ArrayList<>();
		ReviewVO reviewVO;
		
		for(int i=0; i<orderDummy.size(); i++) {
			reviewVO = new ReviewVO();
			
			reviewVO.setOrderIndex(orderDummy.get(i).getOrderIndex());
			reviewVO.setProductIndex(orderOptionMap.get(reviewVO.getOrderIndex()).getProductIndex());
			reviewVO.setReviewIndex(makeReviewIndex(reviewVO.getProductIndex()));
			reviewVO.setReviewDate(orderDummy.get(i).getOrderDate());
			reviewVO.setReviewContent(makeReviewContent());
			
			log.info(reviewVO.toString());
			
			reviewList.add(reviewVO);
		}
		
		
		log.info("직렬화 시작");
		
		String fileLocation = new File("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser").getAbsolutePath() 
								+ "\\ReviewListDummy.ser";
		
		try (FileOutputStream     fos = new FileOutputStream(fileLocation);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);) 
		{
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(reviewList);
			
		} catch (IOException e) {
			
			log.error("직렬화 저장 에러 : " + e);
			e.printStackTrace();
		} 
		
		log.info("직렬화 종료");	
	}
	
	@SuppressWarnings("unchecked")
	public List<ReviewVO> getReviewListFromSerFile(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);
		List<ReviewVO> reviewList = null;
		
		try (FileInputStream     fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);) {
		
			ObjectInputStream in = new ObjectInputStream(bis);
			
			reviewList = (List<ReviewVO>)in.readObject();
			
		} catch (IOException e) {
			log.error("역직렬화 에러 : " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("직렬화 파일 로딩 오류 : " + e);
			e.printStackTrace();
		}
		
		return reviewList;
	} 
	
	
}
