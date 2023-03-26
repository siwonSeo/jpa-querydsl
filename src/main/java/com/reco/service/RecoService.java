package com.reco.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.reco.code.ErrorCode;
import com.reco.common.ApiException;
import com.reco.dto.CollectionInfoResponseDto;
import com.reco.dto.CollectionSpecResponseDto;
import com.reco.dto.StoreInfoResponseDto;
import com.reco.dto.StoreRegistRequestDto;
import com.reco.dto.StoreRegistResponseDto;
import com.reco.entity.Store;
import com.reco.repository.StoreRepository;
import com.reco.repository.StoreRepositoryImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecoService {
	private final StoreRepository storeRepository;
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
