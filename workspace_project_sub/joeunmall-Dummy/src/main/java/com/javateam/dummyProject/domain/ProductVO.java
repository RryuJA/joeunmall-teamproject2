package com.javateam.dummyProject.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * 상품정보 VO
 * 
 * @author RJA
 */
public class ProductVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** (PK)상품번호 : 00_00_000 */
	private String productIndex;
	
	/** (FK)카테고리번호 : 00 */
	private String productCategoryIndex;
	
	/** (FK)판매상태번호 : 0 또는 1 */
	private String productStateIndex;
	
	/** 상품명 */
	private String productName;
	
	/** 상품가격 */
	private int productPrice;
	
	/** 대표이미지 */
	private String productImage;
	
	/** 등록일 */
	private Date productDate;
	
	/** 상세정보 */
	private String productInfo;

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}

	public String getProductCategoryIndex() {
		return productCategoryIndex;
	}

	public void setProductCategoryIndex(String productCategoryIndex) {
		this.productCategoryIndex = productCategoryIndex;
	}

	public String getProductStateIndex() {
		return productStateIndex;
	}

	public void setProductStateIndex(String productStateIndex) {
		this.productStateIndex = productStateIndex;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	@Override
	public String toString() {
		return "ProductVO [productIndex=" + productIndex + ", productCategoryIndex=" + productCategoryIndex
				+ ", productStateIndex=" + productStateIndex + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productImage=" + productImage + ", productDate=" + productDate + ", productInfo="
				+ productInfo + "]";
	}
		
}
