package com.reco.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.reco.dto.CollectionInfoResponseDto;
import com.reco.dto.CollectionSpecResponseDto;
import com.reco.dto.StoreInfoResponseDto;
import com.reco.dto.StoreRegistRequestDto;
import com.reco.entity.Store;

public interface StoreRepositoryCustom{
	List<CollectionSpecResponseDto> findCollectionSpec(Integer id, LocalDate reqRealCollectionDate);
	List<StoreInfoResponseDto> findStoreInfo(Integer id);
	Page<CollectionInfoResponseDto> findCollectionInfo(Integer id, Pageable pageable);
	Store findAlreadyStoreInfo(StoreRegistRequestDto storeInput);
}
