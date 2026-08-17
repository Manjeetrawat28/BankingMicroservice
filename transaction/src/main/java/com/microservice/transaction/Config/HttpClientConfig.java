package com.microservice.transaction.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class HttpClientConfig {
		
	@Value("${http.client.response-timeout}")
    private int responseTimeout;
	
	    @Bean
	    public CloseableHttpClient httpClient() {

	        PoolingHttpClientConnectionManager connectionManager =
	                new PoolingHttpClientConnectionManager();

	        connectionManager.setMaxTotal(200);
	        connectionManager.setDefaultMaxPerRoute(50);

	        ConnectionConfig connectionConfig = ConnectionConfig.custom()
	                .setConnectTimeout(Timeout.ofSeconds(3))
	                .build();

	        connectionManager.setDefaultConnectionConfig(connectionConfig);

	        RequestConfig requestConfig = RequestConfig.custom()
	                .setResponseTimeout(Timeout.ofSeconds(responseTimeout))
	                .setConnectionRequestTimeout(Timeout.ofSeconds(3))
	                .build();

	        return HttpClients.custom()
	                .setConnectionManager(connectionManager)
	                .setDefaultRequestConfig(requestConfig)
	                .build();
	    }
	}
