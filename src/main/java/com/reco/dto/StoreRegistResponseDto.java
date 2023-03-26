package com.reco.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reco.entity.CollectionHistory;
import com.reco.entity.Store;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class StoreRegistResponseDto{
    private final Integer id;
    private final String storeNm;
    private final String regionNm;
    
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private final LocalDate contractDate;
    private final String buisnessNm;
}
