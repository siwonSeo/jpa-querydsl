package com.reco.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class CollectionPicture{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PICTURE_ID")
    private Long id;
	
	@Column(name = "FILE_NM")
    private String fileNm;
	
	@Column(name = "EXTENSION")
	@Enumerated(EnumType.STRING)
    private ExtensionType extesion;
	
	@Column(name = "COLLECTION_ID")
	private Long collectionId;
	
	@Column(name = "CREATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
    private LocalDateTime createDate;
	
	@Column(name = "UPDATE_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	private LocalDateTime updateDate;
	
    @ManyToOne()
    @JoinColumn(name = "COLLECTION_ID", insertable = false, updatable = false)
	private CollectionHistory collectionHistory;	
	
}
