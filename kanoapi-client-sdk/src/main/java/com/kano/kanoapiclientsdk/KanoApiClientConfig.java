package com.kano.kanoapiclientsdk;


import com.kano.kanoapiclientsdk.client.KanoApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ComponentScan
@ConfigurationProperties("kano.api")
public class KanoApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public KanoApiClient kanoApiClient(){
        return new KanoApiClient(accessKey,secretKey);
    }


}
