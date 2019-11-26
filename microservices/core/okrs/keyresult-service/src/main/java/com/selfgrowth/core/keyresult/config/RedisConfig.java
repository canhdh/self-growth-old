package com.selfgrowth.core.keyresult.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache listKeyResults = new ConcurrentMapCache("keyResultCache");
        Cache keyResult = new ConcurrentMapCache("allKeyResultCache");

        cacheManager.setCaches(Arrays.asList(listKeyResults,keyResult));
        return cacheManager;
    }
}
