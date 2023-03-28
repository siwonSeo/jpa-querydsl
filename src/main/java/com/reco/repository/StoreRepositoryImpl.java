package com.reco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.reco.dto.CollectionInfoResponseDto;
import com.reco.dto.CollectionPictureDto;
import com.reco.dto.CollectionSpecResponseDto;
import com.reco.dto.StoreInfoResponseDto;
import com.reco.dto.StoreRegistRequestDto;
import com.reco.entity.QCollectionHistory;
import com.reco.entity.QCollectionPicture;
import com.reco.entity.QStore;
import com.reco.entity.Store;
import lombok.RequiredArgsConstructor;
import static com.querydsl.core.group.GroupBy.*;

@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	QStore store = QStore.store;
	QCollectionHistory history = QCollectionHistory.collectionHistory;
	QCollectionPicture picture = QCollectionPicture.collectionPicture;	
	
	@Override
	public List<StoreInfoResponseDto> findStoreInfo(Integer id) {
		List<StoreInfoResponseDto> storeInfoResponseDtoList = 
					queryFactory.select(Projections.constructor(StoreInfoResponseDto.class,
                		store.buisnessNm,
                		store.regionNm,
                		history.collectionAmount,
                		history.collectionBarrelCnt))
					.from(store)
					.join(store.collectionHistory,history)
					.where(store.id.eq(id))
					.fetchResults().getResults();
		return storeInfoResponseDtoList;
	}

	@Override
	public Page<CollectionInfoResponseDto> findCollectionInfo(Integer id, Pageable pageable) {
	    QueryResults<CollectionInfoResponseDto> results = 
	    		queryFactory.select(Projections.constructor(CollectionInfoResponseDto.class,
	    				history.id,
	    				history.storeId,
	    				history.collectionAmount,
	    				history.collectionBarrelCnt,
	    				history.attachFileCnt,
	    				history.collectionComment,
	    				history.realCollectionDate
	            ))
	            .from(history)
	            .where(history.storeId.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

	    long total = results.getTotal();

	    return new PageImpl<>(results.getResults(), pageable, total);
	}

	@Override
	public Store findAlreadyStoreInfo(StoreRegistRequestDto storeInput) {
		Store storeInfo = 
			queryFactory.selectFrom(store)
			  .where(store.storeNm.eq(storeInput.getStoreNm())
					  .and(store.regionNm.eq(storeInput.getRegionNm()))
					  .and(store.buisnessNm.eq(storeInput.getBuisnessNm()))
			   ).fetchOne();
		return storeInfo;
	}

	@Override
	public List<CollectionSpecResponseDto> findCollectionSpec(Integer id, LocalDate reqDate) {
		List<CollectionSpecResponseDto> collectionSpecResponseDtoList = 
			queryFactory.selectFrom(history)
			.join(history.storeInfo,store)
			.join(history.collectionPictureList,picture)
			.where(store.id.eq(id)
//					.and(Expressions.dateTemplate(String.class,"DATE_FORMAT({0},{1})",history.realCollectionDate,ConstantImpl.create("%Y-%m-%d")).eq(reqDate.toString())) 
					.and(history.realCollectionDate.between(reqDate.atStartOfDay(),reqDate.plusDays(1).atStartOfDay()))
			)
			.transform(groupBy(history.id).list(Projections.constructor(CollectionSpecResponseDto.class 
					,store.storeNm
					,store.regionNm
					,store.buisnessNm
					,history.collectionAmount
					,history.collectionBarrelCnt
					,history.realCollectionDate
					,list(Projections.constructor(CollectionPictureDto.class,picture.fileNm,picture.extesion))
					)));	
			return collectionSpecResponseDtoList;
	}

}
