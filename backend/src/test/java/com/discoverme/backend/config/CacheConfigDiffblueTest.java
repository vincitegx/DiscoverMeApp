package com.discoverme.backend.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CacheConfig.class})
@ExtendWith(SpringExtension.class)
class CacheConfigDiffblueTest {
    @Autowired
    private CacheConfig cacheConfig;

    /**
     * Method under test: {@link CacheConfig#cacheManager()}
     */
    @Test
    void testCacheManager() {
        // Arrange, Act and Assert
        assertTrue(cacheConfig.cacheManager().getCacheNames() instanceof Set);
    }

    /**
     * Method under test: {@link CacheConfig#evictAllCaches()}
     */
    @Test
    void testEvictAllCaches() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CacheConfig cacheConfig = new CacheConfig();

        // Act
        cacheConfig.evictAllCaches();

        // Assert
        CacheManager cacheManagerResult = cacheConfig.cacheManager();
        Collection<String> cacheNames = cacheManagerResult.getCacheNames();
        assertEquals(1, cacheNames.size());
        assertFalse(((ConcurrentMapCacheManager) cacheManagerResult).isStoreByValue());
        assertTrue(cacheNames.contains(CacheConfig.CALENDER));
        assertTrue(((ConcurrentMapCacheManager) cacheManagerResult).isAllowNullValues());
    }

    /**
     * Method under test: {@link CacheConfig#evictAllCaches()}
     */
    @Test
    void testEvictAllCaches2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        cacheConfig.evictAllCaches();
    }
}
