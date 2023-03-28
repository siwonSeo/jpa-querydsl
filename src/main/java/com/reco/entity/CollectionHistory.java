package com.reco.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class CollectionHistory{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COLLECTION_ID")
    private Long id;
	
	@Column(name = "STORE_ID")
    private Integer storeId;
	
	@Column(name = "COLLECTION_AMOUNT")
    private Integer collectionAmount;
	
	@Column(name = "COLLECTION_BARREL_CNT")
	private Integer collectionBarrelCnt;
	
	@Column(name = "ATTACH_FILE_CNT")
	private Integer attachFileCnt;
	
	@Column(name = "COLLECTION_COMMENT")
    private String collectionComment;
	
	@Column(name = "REAL_COLLECTION_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
    private LocalDateTime realCollectionDate;
	
    @ManyToOne()
    @JoinColumn(name = "STORE_ID", insertable = false, updatable = false)
	private Store storeInfo;
	
	@OneToMany(mappedBy="collectionHistory")
	private List<CollectionPicture> collectionPictureList = new ArrayList<>();

	public void addCollectionPicture(CollectionPicture collectionPicture){
		collectionPicture.setCollectionHistory(this);
		this.collectionPictureList.add(collectionPicture);
	}

	@Builder
	public CollectionHistory(Integer storeId, Integer collectionAmount, Integer collectionBarrelCnt, Integer attachFileCnt, String collectionComment) {
		this.storeId = storeId;
		this.collectionAmount = collectionAmount;
		this.collectionBarrelCnt = collectionBarrelCnt;
		this.attachFileCnt = attachFileCnt;
		this.collectionComment = collectionComment;
	}

	public void setStore(Store storeInfo){
		this.storeInfo = storeInfo;
	}
}
