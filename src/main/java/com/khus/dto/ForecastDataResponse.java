package com.khus.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastDataResponse {

	@JsonProperty(value = "list")
	private List<ForecastDto> data;
	
	public List<ForecastDto> getData() {
		return data;
	}
	
	public void setData(List<ForecastDto> data) {
		this.data = data;
	}

}
