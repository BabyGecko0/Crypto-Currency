package aleks.crypto.api.service.serviceImpl;

import aleks.crypto.api.domain.HiWorld;
import aleks.crypto.api.domain.CryptoCurrencyResponse;
import aleks.crypto.api.service.CryptoCurrencyConverterImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Configuration
public class CryptoCurrencyConverter implements CryptoCurrencyConverterImpl {

    @Value("${api-url}")
    private String apiUrl;

    @Value("${api-key}")
    private String apiKey;

    public static CacheManager cacheManager;

    @Override
    @Cacheable(value = "cryptoApi")
    public CryptoCurrencyResponse callAPI(String symbols) {

        String url = apiUrl + "?access_key=" + apiKey + "&symbols=" +  symbols;

        return new RestTemplate().getForObject(url, CryptoCurrencyResponse.class);
    }

    public HiWorld doSomething(){
        return new HiWorld("I dont want to say hello");
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void clearCache() {
        cacheManager.getCache("cryptoApi").clear();
    }
}
