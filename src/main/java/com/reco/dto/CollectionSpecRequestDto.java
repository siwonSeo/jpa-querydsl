package com.reco.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class CollectionSpecRequestDto{
    private Long collectionId;
    private Integer storeId;
    private int collectionAmount;
	private int collectionBarrelCnt;
	private int attachFileCnt;
    private String collectionComment;
    
    public CollectionSpecRequestDto(Long collectionId, Integer storeId, Integer collectionAmount,
			Integer collectionBarrelCnt, Integer attachFileCnt, String collectionComment,
			LocalDateTime realCollectionDate) {
		this.collectionId = collectionId;
		this.storeId = storeId;
		this.collectionAmount = collectionAmount;
		this.collectionBarrelCnt = collectionBarrelCnt;
		this.attachFileCnt = attachFileCnt;
		this.collectionComment = collectionComment;
		this.realCollectionDate = realCollectionDate;
	}
	private LocalDateTime realCollectionDate;
}
