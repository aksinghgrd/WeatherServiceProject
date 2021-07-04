package com.khus.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khus.dto.ForecastDto;
import com.khus.repository.WeatherForeCastReposirory;
import com.khus.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	public final int DAY = 3;
	private final String SUNNY_WEATHER = "Use sunscreen lotion";
	private final String RAIN = "Rain";
	private final String RAINY_WEATHER = "Carry umbrella";
	private final BigDecimal MAX_TEMP = BigDecimal.valueOf(40);
	

	@Autowired
	private WeatherForeCastReposirory repository;

	@Override
	public Map<String, String> getforeCastDetails(String city) {

		Map<String, String> result = new TreeMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_YEAR, 1);

		for (int i = 0; i < DAY; i++) {

			Date startDate = cal.getTime();
			
			String strDate = formatter.format(startDate);

			cal.add(Calendar.DAY_OF_YEAR, 1);

			Date endDate = cal.getTime();
			List<ForecastDto> list = repository.getForeCastData(city).stream()
					.filter(n -> n.getDate().after(startDate) && n.getDate().before(endDate))
					.collect(Collectors.toList());
			

			if (list.stream().anyMatch(n -> n.getTemp().compareTo(MAX_TEMP) > 0))
				result.put(strDate, SUNNY_WEATHER);
			
			else if(list.stream().filter(n -> n.getClimate().contains(RAIN)).findAny().isPresent())
					result.put(strDate, RAINY_WEATHER);
			
			else 
				result.put(strDate, "");

		}

		return result;

	}
}
