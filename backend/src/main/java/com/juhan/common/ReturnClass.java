package com.juhan.common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReturnClass<E> {
	private String result;	// 결과값
	private String message;	// 메세지
	private E obj;			// 결과 객체
	
	public ReturnClass(String result, String message, E obj) {
		this.result = result;
		this.message = message;
		this.obj = obj;
	}
}
