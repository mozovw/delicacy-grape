package com.delicacy.grape.resttemplate.config;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;


@Slf4j
@UtilityClass
public class RestTemplateUtil {


    public static <P, R> ResponseEntity<R> postForEntity(RestTemplate restTemplate, String url, Class<R> responseType, Map<String, Object> paramMap) {
        return postForEntity(restTemplate, url, MediaType.APPLICATION_FORM_URLENCODED, responseType, null, paramMap);
    }

    public static <P, R> ResponseEntity<R> postForEntity(RestTemplate restTemplate, String url, Class<R> responseType, P p, Map<String, Object> paramMap) {
        return postForEntity(restTemplate, url, MediaType.APPLICATION_JSON_UTF8, responseType, p, paramMap);
    }

    public static <P, R> ResponseEntity<R> postForEntity(RestTemplate restTemplate, String url, Class<R> responseType, P p) {
        return postForEntity(restTemplate, url, MediaType.APPLICATION_JSON_UTF8, responseType, p, null);
    }


    public static <P, R> ResponseEntity<R> postForEntity(RestTemplate restTemplate, String url, MediaType mediaType, Class<R> responseType, P p, Map<String, Object> paramMap) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setAccept(Arrays.asList(MediaType.ALL));
        ResponseEntity<R> responseEntity;
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        if (paramMap != null) {
            for (Map.Entry<String, Object> me : paramMap.entrySet()) {
                params.add(me.getKey(), me.getValue());
            }
        }
        HttpEntity<Object> httpEntity;

        if (MediaType.APPLICATION_FORM_URLENCODED.equals(mediaType)) {
            httpEntity = new HttpEntity<>(params, headers);
        } else {
            httpEntity = new HttpEntity<>(p, headers);
        }


        try {
            responseEntity = restTemplate
                    .postForEntity(url, httpEntity, responseType, params);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error:{}", e.getResponseBodyAsString());
            responseEntity = ResponseEntity.status(e.getStatusCode().value()).build();
        } catch (Exception e) {
            log.error("Error:{}", e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return responseEntity;
    }

    public static <R> ResponseEntity<R> getForEntity(RestTemplate restTemplate, String url, Class<R> responseType, Map<String, String> paramMap) {
        ResponseEntity<R> responseEntity;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (paramMap != null) {
            for (Map.Entry<String, String> me : paramMap.entrySet()) {
                params.add(me.getKey(), me.getValue());
            }
        }
        try {
            responseEntity = restTemplate
                    .getForEntity(url, responseType, params);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error:{}", e.getResponseBodyAsString());
            responseEntity = ResponseEntity.status(e.getStatusCode().value()).build();
        } catch (Exception e) {
            log.error("Error:{}", e.getMessage());
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return responseEntity;
    }
}
