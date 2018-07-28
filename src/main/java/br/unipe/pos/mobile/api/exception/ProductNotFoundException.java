package br.unipe.pos.mobile.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	private String productName;
	private String fieldName;
	private Object fieldValue;

	public ProductNotFoundException(String productName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", productName, fieldName, fieldValue));
		this.productName = productName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getProductName() {
		return productName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

}
