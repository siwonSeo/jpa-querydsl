package com.reco.common;

import com.reco.code.ErrorCode;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
	private ErrorCode error;
	private String message;
	
    public ApiException(ErrorCode e) {
        this(e,e.getMessage());
    }	
    
	public ApiException(ErrorCode e, String message) {
    	super(message);
    	this.error = e;
    	this.message = message;
    }

}
