package com.reco.dto;

import com.reco.entity.ExtensionType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CollectionPictureResponseDto{
	private String fileNm;
	private ExtensionType extesion;
	
	public CollectionPictureResponseDto(String fileNm, ExtensionType extesion) {
		this.fileNm = fileNm;
		this.extesion = extesion;
	}
}
