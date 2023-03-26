package com.reco.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CollectionSpecResponseDto{
    private String storeNm;
    private String regionNm;
    private String buisnessNm;
    private int collectionAmount;
	private int collectionBarrelCnt;    
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime realCollectionDate;
    private List<CollectionPictureResponseDto> pictureList;
    
	public CollectionSpecResponseDto(String storeNm, String regionNm, String buisnessNm, Integer collectionAmount,
			Integer collectionBarrelCnt, LocalDateTime realCollectionDate, List<CollectionPictureResponseDto> pictureList) {
		this.storeNm = storeNm;
		this.regionNm = regionNm;
		this.buisnessNm = buisnessNm;
		this.collectionAmount = collectionAmount;
		this.collectionBarrelCnt = collectionBarrelCnt;
		this.realCollectionDate = realCollectionDate;
		this.pictureList = pictureList;
	}
}
