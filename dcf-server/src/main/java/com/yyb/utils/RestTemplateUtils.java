package com.yyb.utils;

import com.yyb.entity.xueqiu.RestResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class RestTemplateUtils {
    @Autowired
    private RestTemplate restTemplate;

    public String getCookies(String url) {
        String cookie = "";
        ResponseEntity<String> exchange = restTemplate.getForEntity(url, String.class);
        List<String> cookies = exchange.getHeaders().get("Set-Cookie");
        if (!CollectionUtils.isEmpty(cookies)) {
            cookie = String.join(";", cookies);
        }
        return cookie;
    }

    public <T> RestResultEntity<T> httpGetRestResultEntity(String url, String cookie, ParameterizedTypeReference<RestResultEntity<T>> parameterizedTypeReference) {
        if (parameterizedTypeReference == null) return null;
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, Collections.singletonList(cookie));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<RestResultEntity<T>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, parameterizedTypeReference);
        RestResultEntity<T> body = responseEntity.getBody();
        return body;
    }

    public <T> T httpGet(String url, String cookie, ParameterizedTypeReference<T> parameterizedTypeReference) {
        if (parameterizedTypeReference == null) return null;
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, Collections.singletonList(cookie));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, parameterizedTypeReference);
        T body = responseEntity.getBody();
        return body;
    }
}
