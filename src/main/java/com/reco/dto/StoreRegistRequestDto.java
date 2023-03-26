package com.reco.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.reco.entity.Store;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreRegistRequestDto{
	@NotEmpty(message="업장 이름은 필수입니다.")
	@Size(max = 100, message="업장 이름은 {max}자 이하여야 합니다.")
    private final String storeNm;
	
	@NotEmpty(message="지역은 필수입니다.")
	@Size(max = 20, message="지역은 {max}자 이하여야 합니다.")
    private final String regionNm;
	
	@NotEmpty(message="사업자 이름은 필수입니다.")
	@Size(max = 100, message="사업자 이름은 {max}자 이하여야 합니다.")
    private final String buisnessNm;
	
    public Store toStoreEntity(){
        return Store.builder()
                .storeNm(this.storeNm)
                .regionNm(this.regionNm)
                .buisnessNm(this.buisnessNm)
                .build();
    }	
}
