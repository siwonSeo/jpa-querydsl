package com.reco.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.reco.dto.*;
import com.reco.entity.CollectionHistory;
import com.reco.entity.CollectionPicture;
import com.reco.repository.CollectionHistoryRepository;
import com.reco.repository.CollectionPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reco.code.ErrorCode;
import com.reco.common.ApiException;
import com.reco.entity.Store;
import com.reco.repository.StoreRepository;
import com.reco.repository.StoreRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecoService {
	private final StoreRepository storeRepository;
	private final CollectionHistoryRepository collectionHistoryRepository;
	private final CollectionPictureRepository collectionPictureRepository;
    private final StoreRepositoryImpl storeRepositoryImpl;
    
    @Transactional
	public StoreRegistResponseDto registStoreInfo(final StoreRegistRequestDto storeInput) {
		Store store = storeRepositoryImpl.findAlreadyStoreInfo(storeInput);
		if(store != null) {
			throw new ApiException(ErrorCode.ALREADY_REGIST_STORE);
		}
		
		Store resultStore = storeRepository.save(storeInput.toStoreEntity());
		return StoreRegistResponseDto.builder()
			.id(resultStore.getId())
			.storeNm(resultStore.getStoreNm())
			.buisnessNm(resultStore.getBuisnessNm())
			.regionNm(resultStore.getRegionNm())
			.contractDate(resultStore.getContractDate())
			.build();
	}

	@Transactional
	public PicturesRegistResponseDto registPicturesInfo(final PicturesRegistRequestDto picturesInput) {
		Store store = storeRepository.findById(picturesInput.getId()).orElseThrow(()->{throw new ApiException(ErrorCode.NOT_FOUND_STORE);});

		CollectionHistory history = collectionHistoryRepository.save(picturesInput.toHistoryEntity());

		/*
		for(CollectionPictureDto collectionPictureDto: picturesInput.getPictureList()){
			CollectionPicture picture = CollectionPicture.builder()
					.collectionId(history.getId())
					.fileNm(collectionPictureDto.getFileNm())
					.extesion(collectionPictureDto.getExtesion())
					.build();
			picture.setCollectionHistory(history);
			collectionPictureRepository.save(picture);
		}
		*/

		List<CollectionPicture> pictureList = new ArrayList<>();

		for(CollectionPictureDto collectionPictureDto: picturesInput.getPictureList()){
			CollectionPicture picture = CollectionPicture.builder()
					.collectionId(history.getId())
					.fileNm(collectionPictureDto.getFileNm())
					.extesion(collectionPictureDto.getExtesion())
					.build();
			picture.setCollectionHistory(history);
			pictureList.add(picture);
		}

		collectionPictureRepository.saveAll(pictureList);

		return PicturesRegistResponseDto.builder()
				.id(history.getStoreId())
				.collectionId(history.getId())
				.collectionAmount(history.getCollectionAmount())
				.collectionBarrelCnt(history.getCollectionBarrelCnt())
				.collectionComment(history.getCollectionComment())
				.attachFileCnt(history.getAttachFileCnt())
				.realCollectionDate(history.getRealCollectionDate())
				.build();
	}

    public List<CollectionSpecResponseDto> getCollectionSpec(Integer storeId, LocalDate reqRealCollectionDate) {
    	List<CollectionSpecResponseDto> collectionSpecResponseDto = storeRepositoryImpl.findCollectionSpec(storeId, reqRealCollectionDate);
    	return collectionSpecResponseDto;
    }
    
	public List<StoreInfoResponseDto> getStoreInfo(Integer storeId) {
		List<StoreInfoResponseDto> storeInfo = storeRepositoryImpl.findStoreInfo(storeId);
		return storeInfo;
	}
	
	public Page<CollectionInfoResponseDto> getCollectionInfo(Integer storeId, Pageable pageable) {
		Page<CollectionInfoResponseDto> collectionInfo = storeRepositoryImpl.findCollectionInfo(storeId, pageable);
		return collectionInfo;
	}
}
