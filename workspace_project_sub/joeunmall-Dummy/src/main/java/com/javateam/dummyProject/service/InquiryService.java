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
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.javateam.dummyProject.domain.InquiryVO;
import com.javateam.dummyProject.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author RJA
 */

@Service
@Slf4j
public class InquiryService {
	
	/** USER_TBL에 저장된 고객정보 50명 랜덤순서로 저장 */
	private List<UserVO> userDummy;
	
	private String month = "08";
	private String day = "10";
	
	private int randomNum = 0;
	private int nowNum = 10;
	
	/**
	 * UserDAO.selectUserTBLRandom() 결과를 가져옴
	 * @param userDummy
	 */
	public void setUserDummy(List<UserVO> userDummy) {
		this.userDummy = userDummy;
	}
	
	/**
	 * 문의번호 형식 : ﻿000000000
	 * ﻿'문의일자 6자리' (+) '해당일자 문의순서 3자리'
	 * @return 문의번호
	 */
	private String makeInquiryIndex() {
		if(randomNum < nowNum) {
			randomNum = (int)Math.floor(Math.random()*5 + 1);
			nowNum = 1;
			
			//일자 변경
			day = String.format("%02d", (Integer.parseInt(day) + (int)(Math.random()*6 + 1)));
			
			//일자가 30일이 넘어갈 경우, 다음달 1일로 변경
			if(Integer.parseInt(day) > 30) {
				day = "01";
				month = Integer.parseInt(month) < 9 ? 
								"0" + (Integer.parseInt(month) + 1) : (Integer.parseInt(month) + 1) + "";
			}
		}	
		
		String result = "22";
		result += month + day + String.format("%03d", nowNum);
		nowNum += 1;

		return result;
	}
	
	/**
	 * 고객Dummy에서 고객 랜덤호출
	 * @return 고객번호
	 */	
	private String returnUserIndex() {
		String result = userDummy.get(0).getUserIndex();
		userDummy.remove(0);
		return result;
	}
	
	/**
	 * @return 문의일자
	 */
	private Date makeInqiryDate() {
		Date date = new Date();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {	// parse 함수 사용 시 ParseException 처리 필요
			date = format.parse("2022" + month + day);
			return date;
		} catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 문의항목
	 * 상품, 교환/환불 -> 랜덤출력
	 * @return 문의항목
	 */
	private String makeInquiryCategory() {
		String result;
		String category[] = {"상품", "교환/환불"};
		
		result = category[(int)Math.floor(Math.random() * category.length)];
		
		return result;
	}

	// 더미데이터 리스트
	public void dummyData() {
		List<InquiryVO> inquiryList = new ArrayList<>();
		InquiryVO inquiryVO;
		
		for(int i=0; i<50; i++) {
			inquiryVO = new InquiryVO();
			
			inquiryVO.setInquiryIndex(makeInquiryIndex());
			inquiryVO.setUserIndex(returnUserIndex());
			inquiryVO.setInquiryDate(makeInqiryDate());
			inquiryVO.setInquiryCategory(makeInquiryCategory());
			inquiryVO.setInquiryState("답변완료");
			inquiryVO.setInquiryTitle(inquiryVO.getInquiryIndex() + " 문의제목 dummy");
			inquiryVO.setInquiryContent(inquiryVO.getInquiryIndex() + "문의내용 dummy");
			inquiryVO.setInquiryAnswer(inquiryVO.getInquiryIndex() + "문의답변 dummy");
			
			log.info(inquiryVO.toString());
			
			inquiryList.add(inquiryVO);
		}
		
		
		log.info("직렬화 시작");
		
		String fileLocation = new File("C:\\joeunmall-teamproject2\\workspace_project_sub\\joeunmall-Dummy\\src\\main\\resources\\ser").getAbsolutePath() 
								+ "\\InquiryListDummy.ser";
		
		try (FileOutputStream     fos = new FileOutputStream(fileLocation);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);) 
		{
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(inquiryList);
			
		} catch (IOException e) {
			
			log.error("직렬화 저장 에러 : " + e);
			e.printStackTrace();
		} 
		
		log.info("직렬화 종료");	
	}
	
	@SuppressWarnings("unchecked")
	public List<InquiryVO> getInquiryListFromSerFile(String filePath) throws FileNotFoundException {
		
		File file = new File(filePath);
		List<InquiryVO> inquiryList = null;
		
		try (FileInputStream     fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);) {
		
			ObjectInputStream in = new ObjectInputStream(bis);
			
			inquiryList = (List<InquiryVO>)in.readObject();
			
		} catch (IOException e) {
			log.error("역직렬화 에러 : " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("직렬화 파일 로딩 오류 : " + e);
			e.printStackTrace();
		}
		
		return inquiryList;
	} 
	
	
}
