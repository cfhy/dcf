package com.yyb.utils;

import com.yyb.entity.dfcf.RestResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class DfcfRestTemplateUtils {
    @Autowired
    private RestTemplate restTemplate;

    public <T> RestResultEntity<T> httpGetRestResultEntity(String url,ParameterizedTypeReference<RestResultEntity<T>> parameterizedTypeReference) {
        if (parameterizedTypeReference == null) return null;
        ResponseEntity<RestResultEntity<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, parameterizedTypeReference);
        RestResultEntity<T> body = responseEntity.getBody();
        return body;
    }
}
