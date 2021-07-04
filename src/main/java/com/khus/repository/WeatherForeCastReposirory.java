package com.khus.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.khus.dto.ForecastDataResponse;
import com.khus.dto.ForecastDto;

import lombok.Getter;

@Getter
@Repository
public class WeatherForeCastReposirory {

	@Value("${api.url}")
	private String uri;

	@Value("${api.key}")
	private String apiKey;

	public List<ForecastDto> getForeCastData(String city) {
		List<ForecastDto> result = null;
		try {
			
			 RestTemplate restTemplate = new RestTemplate();
			 HttpHeaders headers = new HttpHeaders();
			 headers.set("Accept", "application/json");

			 Map<String, String> params = new HashMap<String, String>();
			 params.put("q", city);
			 params.put("appid", apiKey);
			

			ForecastDataResponse response = restTemplate.getForObject(
			     "http://api.openweathermap.org/data/2.5/forecast?q=Sydney&appid=0694a7368ae282caa01f70fb627469ce", ForecastDataResponse.class, params);
			 
		
			result = response.getData();
			//result.forEach(n -> System.out.println(n.getDate() + " --- " + n.getTemp() + " ==" + n.getClimate()));
			
		} catch (Exception e) {
			System.out.println(e);
		}

		
		return result;

	}

}
