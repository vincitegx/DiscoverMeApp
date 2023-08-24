package com.discoverme.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
    public static final String SUPPORTING_PROJECTS = "supporting-projects";
    public static final String APPROVED_PROJECTS = "approved-projects";

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager(CALENDER, SUPPORTING_PROJECTS, APPROVED_PROJECTS);
    }
    
    @Scheduled(fixedRate = 3599999)
    public void evictAllCaches(){
        try{
            cacheManager().getCache(CALENDER).clear();
            cacheManager().getCache(SUPPORTING_PROJECTS).clear();
            cacheManager().getCache(APPROVED_PROJECTS).clear();
        }catch(NullPointerException ex){
            log.error(ex.getLocalizedMessage());
        }
    }    
}
