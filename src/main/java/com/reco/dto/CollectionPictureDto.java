package com.reco.dto;

import com.reco.entity.ExtensionType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CollectionPictureDto {
	private String fileNm;
	private ExtensionType extesion;
	
	public CollectionPictureDto(String fileNm, ExtensionType extesion) {
		this.fileNm = fileNm;
		this.extesion = extesion;
	}
}
