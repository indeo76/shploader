package pl.wodnet.shploader;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.cache.CacheBuilder;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES)
                        .maximumSize(100000).build().asMap(), true);
            }
        };

        cacheManager.setCacheNames(Arrays.asList("findTargetTable"));
        return cacheManager;
    }
}
