package com.javateam.dummyProject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javateam.dummyProject.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author RJA
 */

@Repository
@Slf4j
public class ProductDAO {
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private final String PRODUCT_INSERT = "INSERT INTO product_tbl VALUES("
											+ "?, ?, ?, ?, ?, "		//상품번호, 카테고리번호, 판매상태번호, 상품명, 상품가격
											+ "?, ?, ?)";			//대표이미지, 등록일, 상세정보

	private final String PRODUCT_SELECT_RANDOM = "SELECT * FROM ( SELECT * FROM product_tbl ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 200";
	
	private final String PRODUCT_SELECT_ALL = "SELECT * FROM product_tbl";
    
	public void insertProductTBL(ProductVO vo) {
		log.info("PRODUCT_TBL INSERT");
		jdbcTemplate.update(PRODUCT_INSERT, 
							vo.getProductIndex(), vo.getProductCategoryIndex(), vo.getProductStateIndex(), vo.getProductName(), 
							vo.getProductPrice(), vo.getProductImage(), vo.getProductDate(), vo.getProductInfo());
		
	}
	
	public List<ProductVO> selectProductTBLRandom() {
		log.info("PRODUCT_TBL SELECT RANDOM DATA");
		
		List<ProductVO> vo = jdbcTemplate.query(PRODUCT_SELECT_RANDOM, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO product = new ProductVO();
				
				product.setProductIndex(rs.getString(1));
				product.setProductCategoryIndex(rs.getString(2));
				product.setProductStateIndex(rs.getString(3));
				product.setProductName(rs.getString(4));
				product.setProductPrice(rs.getInt(5));
				product.setProductImage(rs.getString(6));
				product.setProductDate(rs.getDate(7));
				product.setProductInfo(rs.getString(8));
				
				return product;
			}
		});
		
		return vo;		
	}
	
	public List<ProductVO> selectProductTBLAll() {
		log.info("PRODUCT_TBL SELECT ALL DATA");
		
		List<ProductVO> vo = jdbcTemplate.query(PRODUCT_SELECT_ALL, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO product = new ProductVO();
				
				product.setProductIndex(rs.getString(1));
				product.setProductCategoryIndex(rs.getString(2));
				product.setProductStateIndex(rs.getString(3));
				product.setProductName(rs.getString(4));
				product.setProductPrice(rs.getInt(5));
				product.setProductImage(rs.getString(6));
				product.setProductDate(rs.getDate(7));
				product.setProductInfo(rs.getString(8));
				
				return product;
			}
		});
		
		return vo;		
	}
}
