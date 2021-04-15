package com.saas.common.core.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis cache工具类
 * 
 * @author bruce
 *
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache {
	@Autowired
	private RedisTemplate redisTemplate;

	// 本地缓存，如果差异性允许几秒延时，可以短暂缓存在本地，应对高并发网络开销
	@Resource(name = "localCache")
	private Cache<String, Serializable> localCache;

	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 *
	 * @param key   缓存的键值
	 * @param value 缓存的值
	 */
	public <T> void setCacheObject(final String key, final T value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 原子加操作
	 * 
	 * @param key             redis关键字
	 * @param delta           增加数值
	 * @param initValue       初始的数值
	 * @param forceCheckExist 是否强制检查key的存在
	 * @return
	 */
	public long increment(String key, long delta, long initValue, boolean forceCheckExist) {
		long value = 0;
		if (forceCheckExist) {
			if (!redisTemplate.hasKey(key)) {
				value = redisTemplate.opsForValue().increment(key, initValue + delta);
			} else {
				value = redisTemplate.opsForValue().increment(key, delta);
			}
		} else {
			value = redisTemplate.opsForValue().increment(key, delta);
			if (value == delta) {
				value = redisTemplate.opsForValue().increment(initValue);
			}
		}
		return value;
	}

	/**
	 * 缓存基本的对象，Long、String、实体类等
	 *
	 * @param key      缓存的键值
	 * @param value    缓存的值
	 * @param timeout  时间
	 * @param timeUnit 时间颗粒度
	 */
	public <T> void setCacheObject(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
	}

	/**
	 * 设置有效时间
	 *
	 * @param key     Redis键
	 * @param timeout 超时时间
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key, final long timeout) {
		return expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置有效时间
	 *
	 * @param key     Redis键
	 * @param timeout 超时时间
	 * @param unit    时间单位
	 * @return true=设置成功；false=设置失败
	 */
	public boolean expire(final String key, final long timeout, final TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 获得缓存的基本对象。
	 *
	 * @param key 缓存键值
	 * @return 缓存键值对应的数据
	 */
	public <T> T getCacheObject(final String key) {
		ValueOperations<String, T> operation = redisTemplate.opsForValue();
		return operation.get(key);
	}

	/**
	 * 删除单个对象
	 *
	 * @param key
	 */
	public boolean deleteObject(final String key) {
		deleteLocalCache(key);
		return redisTemplate.delete(key);
	}

	/**
	 * 删除集合对象
	 *
	 * @param collection 多个对象
	 * @return
	 */
	public long deleteObject(final Collection collection) {
		return redisTemplate.delete(collection);
	}

	/**
	 * 缓存List数据
	 *
	 * @param key      缓存的键值
	 * @param dataList 待缓存的List数据
	 * @return 缓存的对象
	 */
	public <T> long setCacheList(final String key, final List<T> dataList) {
		Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
		return count == null ? 0 : count;
	}

	/**
	 * 获得缓存的list对象
	 *
	 * @param key 缓存的键值
	 * @return 缓存键值对应的数据
	 */
	public <T> List<T> getCacheList(final String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * 缓存Set
	 *
	 * @param key     缓存键值
	 * @param dataSet 缓存的数据
	 * @return 缓存数据的对象
	 */
	public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
		BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
		Iterator<T> it = dataSet.iterator();
		while (it.hasNext()) {
			setOperation.add(it.next());
		}
		return setOperation;
	}

	/**
	 * 获得缓存的set
	 *
	 * @param key
	 * @return
	 */
	public <T> Set<T> getCacheSet(final String key) {
		return redisTemplate.opsForSet().members(key);
	}

	/**
	 * 缓存Map
	 *
	 * @param key
	 * @param dataMap
	 */
	public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
		if (dataMap != null) {
			redisTemplate.opsForHash().putAll(key, dataMap);
		}
	}

	/**
	 * 获得缓存的Map
	 *
	 * @param key
	 * @return
	 */
	public <T> Map<String, T> getCacheMap(final String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 往Hash中存入数据
	 *
	 * @param key   Redis键
	 * @param hKey  Hash键
	 * @param value 值
	 */
	public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	/**
	 * 获取Hash中的数据
	 *
	 * @param key  Redis键
	 * @param hKey Hash键
	 * @return Hash中的对象
	 */
	public <T> T getCacheMapValue(final String key, final String hKey) {
		HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
		return opsForHash.get(key, hKey);
	}

	/**
	 * 获取多个Hash中的数据
	 *
	 * @param key   Redis键
	 * @param hKeys Hash键集合
	 * @return Hash对象集合
	 */
	public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
		return redisTemplate.opsForHash().multiGet(key, hKeys);
	}

	/**
	 * 获得缓存的基本对象列表
	 *
	 * @param pattern 字符串前缀
	 * @return 对象列表
	 */
	public Collection<String> keys(final String pattern) {
		return redisTemplate.keys(pattern);
	}

	/**
	 * 获取同步锁
	 *
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return
	 */
	public boolean lockMaster(String key, long timeout, TimeUnit unit) {
		long lockValue = redisTemplate.opsForValue().increment(key, 1);
		if (lockValue == 1) {
			redisTemplate.expire(key, timeout, unit);
			return true;
		} else {
			return false;
		}
	}

	private Object getLocalCache(String key) {
		return localCache.get(key);
	}

	private void setLocalCache(String key, Object value) {
		if (value instanceof Serializable)
			localCache.put(key, (Serializable) value);
	}

	private void deleteLocalCache(String key) {
		localCache.remove(key);
	}

	private boolean isNull(Object obj) {
		return obj == null || obj instanceof NullObject;
	}

	/**
	 * 处理缓存是NullObject的情况
	 * 
	 * @param obj
	 * @return
	 */
	private Object processReturn(Object obj) {
		if (isNull(obj))
			return null;
		return obj;
	}

	public void setValueDefaultNull(String key, Object value, long timeout, TimeUnit unit, boolean useLocalCache) {
		if (value == null)
			value = new NullObject();
		setCacheObject(key, value, timeout, unit);
		if (useLocalCache)
			setLocalCache(key, value);
	}

	public Object getValueIfNullSet(String key, long timeout, TimeUnit unit, boolean useLocalCache,
			RedisSetValueCallBack redisSetValueCallBack) {
		if (useLocalCache) {
			Object value = getLocalCache(key);
			if (value != null)
				return processReturn(value);
		}

		Object value = getCacheObject(key);
		if (value == null) {
			value = redisSetValueCallBack.getCacheObject();
			setValueDefaultNull(key, value, timeout, unit, useLocalCache);
		} else if (useLocalCache) {
			setLocalCache(key, value);
		}
		return processReturn(value);
	}

}
