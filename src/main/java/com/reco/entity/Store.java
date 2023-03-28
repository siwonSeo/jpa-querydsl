package com.reco.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Store{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STORE_ID")
    private Integer id;
	
	@Column(name = "STORE_NM")
    private String storeNm;
	
	@Column(name = "REGION_NM")
    private String regionNm;
	
	@Column(name = "CONTRACT_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatedDate
    private LocalDate contractDate;
	
	@Column(name = "BUISNESS_NM")
    private String buisnessNm;
	
	@OneToMany(mappedBy="storeInfo")
	List<CollectionHistory> collectionHistory = new ArrayList<>();
	
    @Builder
    public Store(String storeNm, String regionNm, String buisnessNm) {
        this.storeNm = storeNm;
        this.regionNm = regionNm;
        this.buisnessNm = buisnessNm;
    }

    public void addCollectionHistory(CollectionHistory collectionHistory){
        collectionHistory.setStore(this);
        this.collectionHistory.add(collectionHistory);
    }
}
