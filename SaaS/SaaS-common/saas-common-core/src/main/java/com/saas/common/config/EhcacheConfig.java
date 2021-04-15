/**
 * 
 */
package com.saas.common.config;

import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.ExpiryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce
 *
 */
@Configuration
public class EhcacheConfig {

	@Bean
	public CacheManager localCacheManager() {
		// 配置默认缓存属性
		CacheConfiguration<String, Serializable> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
				// 缓存数据K和V的数值类型
				// 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
				String.class, Serializable.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
						// 设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
						.heap(1000L, EntryUnit.ENTRIES)
						// 设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
						.offheap(100L, MemoryUnit.MB)
		// 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
		// .disk(500L, MemoryUnit.MB, false)
		).withExpiry(ExpiryPolicy.NO_EXPIRY).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
				// 设置缓存过期时间
				Duration.of(2L, ChronoUnit.SECONDS))).build();

		// .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(java.time.Duration.of(,
		// ChronoUnit.SECONDS))
		// 设置被访问后过期时间(同时设置和TTL和TTI之后会被覆盖,这里TTI生效,之前版本xml配置后是两个配置了都会生效)

		// CacheManager管理缓存
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
				// 设置一个默认缓存配置
				.withCache("defaultCache", cacheConfiguration)
				// 创建之后立即初始化
				.build(true);
		return cacheManager;

	}

	@Bean(name = "localCache")
	public Cache<String, Serializable> localCache(CacheManager localCacheManager) {
		return localCacheManager.getCache("defaultCache", String.class, Serializable.class);
	}

}
