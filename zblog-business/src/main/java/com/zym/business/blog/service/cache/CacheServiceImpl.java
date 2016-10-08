package com.zym.business.blog.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zym.common.base.service.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

	@Autowired
	private volatile RedisTemplate<String, Object> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(CacheServiceImpl.class);

	public boolean set(final String key, final Object value) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  SET " + key + " = " + value);
		}
		redisTemplate.boundValueOps(key).set(value.toString());
		return true ;
	}
	
	
	public boolean set(final byte[] key, final byte[] value) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  SET " + key + " = " + value);
		}
		redisTemplate.execute(new RedisCallback<Long>() {
	        public Long doInRedis(RedisConnection connection) throws DataAccessException {
	        	connection.set(key, value);
	            return 1l;
	        }
	    });
		return true ;
	}
	
	
	
	
	public boolean set(final String key, final Object value, final Long exp) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  SET " + key + " = " + value);
		}
		return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection){
            	if(exp == null){
                	return false;
                }
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] bkey = serializer.serialize(key);
                byte[] bvalue = serializer.serialize(value.toString());
                connection.setEx(bkey, exp, bvalue);
                return true;
            }
        });
	}
	
	public boolean hasKey(String key ) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  hasKey " + key );
		}
		return redisTemplate.hasKey(key);
	}

	public boolean putHash(String key, Object fieldKey, Object fieldValue) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  putHash " + key + " :{ name= " + fieldKey+" ，value ="+fieldValue+" }");
		}
		if(fieldValue == null){
			return false;
		}
		redisTemplate.boundHashOps(key).put(fieldKey, fieldValue.toString());
		return true ;
	}
	
	public Object getHash(String key, Object fieldKey) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  getHash " + key + " :{ name= " + fieldKey+" }");
		}
		return redisTemplate.boundHashOps(key).get(fieldKey);
	}

    public boolean hasHaskKey(String key,Object fieldKey) {
    	if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  hasHaskKey " + key +":{ name ="+ fieldKey +"}");
		}
        return redisTemplate.boundHashOps(key).hasKey(fieldKey);
    }
	
	public Map<String, String> getHash(String key){
		BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(key);
		if(boundHashOps != null){
			return boundHashOps.entries();
		}
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  getHash " + key );
		}
		return null;
	}
	
	public boolean deleteHash(String key, Object fieldKey) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  deleteHash " + key + " :{ name= " + fieldKey+" }");
		}
		redisTemplate.boundHashOps(key).delete(fieldKey);
		return true;
	}

    @Override
    public boolean deleteHash(String key) {
    	if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  deleteHash " + key );
		}
        redisTemplate.delete(key);
        return true;
    }

	public <T> T get(String key) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  get " + key );
		}
		return (T) redisTemplate.boundValueOps(key).get();
	}
	
	public byte[] getByte(final byte[] key){
		return redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection connection){
            	return connection.get(key);
            }
        });
	}

	public boolean delete(String key) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  delete " + key );
		}
		redisTemplate.delete(key);
		return true;
	}
	
	private RedisSerializer<String> getRedisSerializer() {
		
	    return redisTemplate.getStringSerializer();
	}

	private RedisSerializer<Object> getRedisObjectSerializer() {
		return new RedisSerializer<Object>() {
			
			@Override
			public byte[] serialize(Object t) throws SerializationException {
				ByteArrayOutputStream bStream = new ByteArrayOutputStream();
				try {
					ObjectOutputStream oStream = new ObjectOutputStream(bStream);
					oStream.writeObject(t);
				} catch (IOException e) {
					log.warn(e.getMessage(),e);
				}
				return bStream.toByteArray();
			}

			@Override
			public Object deserialize(byte[] bytes)
					throws SerializationException {
				if(bytes == null) {
					return null;
				}
				ByteArrayInputStream bStream = new ByteArrayInputStream(bytes);
				try {
					ObjectInputStream oStream = new ObjectInputStream(bStream);
					Object object = oStream.readObject();
					return object;
				} catch (IOException e) {
					log.warn(e.getMessage(),e);
				} catch (ClassNotFoundException e) {
					log.warn(e.getMessage(),e);
				}
				return null;
			}
		};
	}
	
	public boolean setSerializer(final String key, final Object value, final Long exp){
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  SET " + key + " = " + value);
		}
        //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(value.getClass()));
		//redisTemplate.boundValueOps(key).set(value, exp, TimeUnit.SECONDS);
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			operations.set(key, objectMapper.writeValueAsString(value), exp, TimeUnit.SECONDS);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true ;
	}

	public boolean setSerializer(String key, Object value)
	{
		if(log.isDebugEnabled()) 
		{
			log.debug("@@@@@@@@@@@  SET " + key + " = " + value);
		}
        //redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(value.getClass()));
		//redisTemplate.boundValueOps(key).set(value);
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			operations.set(key, objectMapper.writeValueAsString(value));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true ;
	}

	public <T> T getSerializer(String key, Class<?> clazz) 
	{
		//redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(clazz));
		//Object res = redisTemplate.boundValueOps(key).get();
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		String json = (String) operations.get(key);
		if(json == null)
		{
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
	    // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try 
		{
			return (T) objectMapper.readValue(json, clazz);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addSetMember(String key, Object... members){
		redisTemplate.boundSetOps(key).add(members);
		return true;
	}
	
	public boolean hasSetMember(String key, Object member){
		return redisTemplate.boundSetOps(key).isMember(member);
	}

	@Override
	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}

	@Override
	public Set<String> getHashTableFields(final String key) {
		return  redisTemplate.execute(new RedisCallback<Set<String>>() {

			@Override
			public Set<String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<byte[]> fields = null;
				try {
					fields = connection.hKeys(key.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				Set<String> fieldSet = new HashSet<String>();
				if(fields!=null){
					Iterator<byte[]> iter = fields.iterator();
					byte[] field ;
					while(iter.hasNext()){
						field = iter.next();
						try {
							fieldSet.add(new String(field,"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
				
				return fieldSet;
			}
			
		});
	}
	
	@Override
	public boolean expire(String key, long timeout) {
		return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	@Override
	public boolean deleteByPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		if (keys!=null&&keys.size() > 0){
			redisTemplate.delete(keys);
		}
		return true;
	}
	
	@Override
	public boolean addZSetMember(String key,Object value,double score) {
		if(log.isDebugEnabled()) {
			log.debug("@@@@@@@@@@@  addZSet " + key + " :{ value= " + value+" ，score ="+score+" }");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			redisTemplate.boundZSetOps(key).add(objectMapper.writeValueAsString(value), score);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public Set<TypedTuple<Object>> zSetWithScores(String key, long start, long end) {
		BoundZSetOperations<String, Object> zo =  redisTemplate.boundZSetOps(key);
		Set<TypedTuple<Object>> set = zo.rangeWithScores(start, end);
		return set;
	}
	@Override
	public Set<TypedTuple<Object>> zSetReverseRangeWithScores(String key, long start, long end) {
		BoundZSetOperations<String, Object> zo =  redisTemplate.boundZSetOps(key);
		Set<TypedTuple<Object>> set = zo.reverseRangeWithScores(start, end);
		return set;
	}
	
	@Override
	public Object zSetscore(String key,String value) {
		BoundZSetOperations<String, Object> zo =  redisTemplate.boundZSetOps(key);
		return zo.score(value);
	}
	/**
	 * 获取缓存大小
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }
}
