package com.discoverme.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

/**
 *
 * @author TEGA
 */
@Configuration
@EnableCaching
@EnableScheduling
@Slf4j
public class CacheConfig {
    
    public static final String CALENDER = "calender";

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(CALENDER);
    }
    
    @Scheduled(fixedRate = 604800000)
    public void evictAllCaches(){
        try{
            Objects.requireNonNull(cacheManager().getCache(CALENDER)).evictIfPresent("calender");
        }catch(NullPointerException ex){
            log.error(ex.getLocalizedMessage());
        }
    }    
}
