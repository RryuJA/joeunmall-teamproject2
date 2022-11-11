package com.javateam.dummyProject.domain;

import java.io.Serializable;

/** 
 * 상품옵션 VO 
 * 
 * @author RJA
 */
public class ProductOptionVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** (PK)상품옵션번호: 00_00_000_OP0 */
	private String productOptionIndex;
	
	/** (FK)상품번호 : 00_00_000 */
	private String productIndex;
	
	/** 상품옵션 */
	private String productOptionValue;

	public String getProductOptionIndex() {
		return productOptionIndex;
	}

	public void setProductOptionIndex(String productOptionIndex) {
		this.productOptionIndex = productOptionIndex;
	}

	public String getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(String productIndex) {
		this.productIndex = productIndex;
	}

	public String getProductOptionValue() {
		return productOptionValue;
	}

	public void setProductOptionValue(String productOptionValue) {
		this.productOptionValue = productOptionValue;
	}

	@Override
	public String toString() {
		return "ProductOptionVO [productOptionIndex=" + productOptionIndex + ", productIndex=" + productIndex
				+ ", productOptionValue=" + productOptionValue + "]";
	}

}
