package com.khus.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForecastDto {

	
	private Date date;
	
	private BigDecimal temp;

	private List<String> climate;
	
	
	@JsonProperty("dt")
	public void setDate(long timeStamp) {
		this.date = new java.util.Date(timeStamp*1000);
	}
	
	public Date getDate() {
		return date;
	}
	
	
    @JsonProperty("main")
	public void setTemp(Map<String, Object> main) {
		this.temp = BigDecimal.valueOf((double)main.get("temp")).subtract(BigDecimal.valueOf(273.15));
	}
    
    public BigDecimal getTemp() {
		return temp;
	}
    
   
    @JsonProperty("weather")
    public void setClimate(List<Map<String, Object>> weather) {
		this.climate =  weather.stream().map(n -> n.get("main").toString()).collect(Collectors.toList());
	}
    
    public  List<String> getClimate() {
		return climate;
	}

}

