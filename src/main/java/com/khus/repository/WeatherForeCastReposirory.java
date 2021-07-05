package com.khus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.khus.dto.ForecastDataResponse;
import com.khus.dto.ForecastDto;

import lombok.Getter;

@Getter
@Repository
public class WeatherForeCastReposirory {

	@Value("${api.uri}")
	private String uri;

	@Value("${api.key}")
	private String apiKey;

	public List<ForecastDto> getForeCastData(String city) {
		List<ForecastDto> result = null;
		try {
			
			RestTemplate restTemplate = new RestTemplate();

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);
			builder.queryParam("q", city);
			builder.queryParam("appid", apiKey);

			ForecastDataResponse response = restTemplate.getForObject(builder.toUriString(),
					ForecastDataResponse.class);

			result = response.getData();

		} catch (Exception e) {
			System.out.println(e);
		}

		return result;

	}

}
