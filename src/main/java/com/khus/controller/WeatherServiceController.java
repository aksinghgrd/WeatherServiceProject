package com.khus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khus.service.WeatherService;

@RestController
@RequestMapping(value = "/api")
public class WeatherServiceController {

	@Autowired
	private WeatherService service;

	@GetMapping(value = "/healthCheck")
	public String healthChecks() {
		return "Success";
	}

	@GetMapping("/getWeatherForecast/{city}")
	public Map<String, String> getWeatherForecast(@PathVariable String city) {
		return service.getforeCastDetails(city);
	}
}
