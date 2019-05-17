package com.videotel.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.videotel.test.domain.RestCountriesResponse;
import com.videotel.test.exception.UserException;

@Component
public class ApiService {

	@Value("${api.url}")
	private String apiUrl;
	@Value("${api.url.field}")
	private String apiUrlField;
	@Value("${api.url.timeout}")
	private int timeOut;

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate restTemplate() {
		restTemplate = new RestTemplate();
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(timeOut);
		return restTemplate;
	}

	public ResponseEntity<RestCountriesResponse> getNationality(String country) {
		final String url = apiUrl + country + "/" + apiUrlField;
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<RestCountriesResponse> nationality = null;
		try {
			nationality = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RestCountriesResponse.class);
		} catch (Exception e) {
			throw new UserException(false, "Not able to fetch the country name from external API");
		}
		return nationality;
	}
}
