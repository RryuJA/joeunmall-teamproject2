package com.javateam.dummyProject.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javateam.dummyProject.domain.UserVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private final String USER_INSERT = "INSERT INTO user_tbl VALUES("
											+ "?, ?, ?, ?, ?, "		//고객번호, 아이디, 이름, 비밀번호, 가입일
											+ "?, ?, ?, ?, ?, "		//이메일, 생년월일, 성별, 일반전화, 휴대전화
											+ "?, ?, ?)";			//우편번호, 주소, 상세주소

    
	public void insertUserTBL(UserVO vo) {
		log.info("USER_TBL INSERT");
		jdbcTemplate.update(USER_INSERT, 
							vo.getUserIndex(), vo.getUserId(), vo.getUserName(), vo.getUserPw(), vo.getUserDate(),
							vo.getUserEmail(), vo.getUserBirth(), vo.getUserGender(), vo.getUserLandline(), vo.getUserMobile(),
							vo.getUserPost(), vo.getUserAddress(), vo.getUserAddressDetail());
	}
}