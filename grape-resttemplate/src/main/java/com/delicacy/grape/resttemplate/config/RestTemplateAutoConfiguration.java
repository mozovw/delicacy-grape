package com.delicacy.grape.resttemplate.config;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ExtractingResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;


@Configuration
public class RestTemplateAutoConfiguration {
	@Value("${rest.config.connectTimeout:10000}")
	private int connectTimeout;
	@Value("${rest.config.readTimeout:30000}")
	private int readTimeout;

	@Bean
    public RestTemplateBootstrap restClientBootstrap(){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		HttpClient httpClient = httpClientBuilder.build();
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.setInterceptors(Collections.singletonList(new AcceptRequestInterceptor()));
		restTemplate.getMessageConverters().stream().filter(e->e instanceof StringHttpMessageConverter).forEach(e->((StringHttpMessageConverter) e).setDefaultCharset(StandardCharsets.UTF_8));
		return new RestTemplateBootstrap();
    }

	static class RestTemplateBootstrap {}

	static class AcceptRequestInterceptor implements ClientHttpRequestInterceptor {
		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
											ClientHttpRequestExecution execution) throws IOException {
			HttpHeaders headers = request.getHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.APPLICATION_FORM_URLENCODED));
			headers.add("uuid", UUID.randomUUID().toString());
			return execution.execute(request, body);
		}
	}
}
