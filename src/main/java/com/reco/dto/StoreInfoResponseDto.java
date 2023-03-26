package com.reco.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreInfoResponseDto{
	private String storeNm;
	private String regionNm;
	private int collectionAmount;
	private int collectionBarrelCnt;
	
	public StoreInfoResponseDto(String storeNm, String regionNm, Integer collectionAmount,
			Integer collectionBarrelCnt) {
		this.storeNm = storeNm;
		this.regionNm = regionNm;
		this.collectionAmount = collectionAmount;
		this.collectionBarrelCnt = collectionBarrelCnt;
	}
}
