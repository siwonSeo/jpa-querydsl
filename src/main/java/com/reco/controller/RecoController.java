package com.reco.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reco.dto.CollectionInfoResponseDto;
import com.reco.dto.CollectionSpecResponseDto;
import com.reco.dto.StoreInfoResponseDto;
import com.reco.dto.StoreRegistRequestDto;
import com.reco.dto.StoreRegistResponseDto;
import com.reco.entity.Store;
import com.reco.service.RecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reco/v1")
@RequiredArgsConstructor
public class RecoController {

	private final RecoService recoService;
	
	@PostMapping("/store-regist")
	ResponseEntity<StoreRegistResponseDto> registStoreInfo(@Valid StoreRegistRequestDto storeInfo) {
		return new ResponseEntity<>(recoService.registStoreInfo(storeInfo),HttpStatus.OK);
	}
	
	@GetMapping("/collection-spec/{id}")
	ResponseEntity<List<CollectionSpecResponseDto>> getCollectionInfo(
			@PathVariable Integer id,
			@RequestParam(value = "reqRealCollectionDate", required = true)
			@Valid 
			@NotEmpty(message="DD")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reqRealCollectionDate) {
		return new ResponseEntity<>(recoService.getCollectionSpec(id, reqRealCollectionDate),HttpStatus.OK);
	}
	
	@GetMapping("/store-spec/{id}")
	ResponseEntity<List<StoreInfoResponseDto>> getStoreInfo(@PathVariable Integer id) {
		return new ResponseEntity<>(recoService.getStoreInfo(id),HttpStatus.OK);
	}
	
	@GetMapping("/collection-history/{id}")
	ResponseEntity<Page<CollectionInfoResponseDto>> getCollectionHistory(@PathVariable Integer id,Pageable pageable) {
		return new ResponseEntity<>(recoService.getCollectionInfo(id,pageable),HttpStatus.OK);
	}
	
}
