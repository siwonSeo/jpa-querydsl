package com.reco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reco.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>, StoreRepositoryCustom {
	
}
