package com.reco.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	INPUT_UNVALID_ERROR("ERROR0001", HttpStatus.BAD_REQUEST, "입력값이 불충분합니다."),
	NOT_FOUND_STORE("ERROR0002", HttpStatus.INTERNAL_SERVER_ERROR, "가게정보가 없습니다."),
	ALREADY_REGIST_STORE("ERROR0003", HttpStatus.INTERNAL_SERVER_ERROR, "이미 가입된 업장입니다."),
	API_UNKNOWN_ERROR("ERROR9999", HttpStatus.INTERNAL_SERVER_ERROR, "알수없는 오류가 발생했습니다.");

	private final String code;
	private final HttpStatus httpStatus;
	private final String message;

	public static ErrorCode getByCode(final String code) {
		for (final ErrorCode e : values()) {
			if (e.code.equals(code))
				return e;
		}
		return ErrorCode.API_UNKNOWN_ERROR;
	}
}
