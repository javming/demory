package com.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private static final Logger log =LoggerFactory.getLogger(RedisUtil.class);
	private static final int webexpire = Config.getInt("redis.webexpire");
	private static final int appexpire = Config.getInt("redis.appexpire");
	private static String redisIp = Config.getString("redis.ip");
	private static int redisPort = Config.getInt("redis.port");
	private static int active = Config.getInt("redis.maxactive");
	private static int idle = Config.getInt("redis.maxidle");
	private static int wait = Config.getInt("redis.maxwait");
	private static JedisPool pool;
    /**
     * 初始化Redis连接池
     */
    private static void initJedisPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(active);
		config.setMaxIdle(idle);
		config.setMaxWait(wait);
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, redisIp,redisPort);
		log.info("jedis连接池初始化完成！");
    }

	/**
	 * 获取jedis
	 */
    public static Jedis getJedis(){
		if(pool == null){
			initJedisPool();
		}
		return pool.getResource();
	}
    /** 
     * 返还到连接池
     */  
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }  
    }

	/**
	 * 回收broken的jedis
	 */
	public static void returnBrokenResource(Jedis jedis){
		if (jedis != null) {
			pool.returnBrokenResource(jedis);
		}
	}

    /**
     *  缓存token 加有效时间expire
	 */
	public static void putRedis(String openId, String token, int expire){
        Jedis jedis = null;
		try {
			log.info("key="+openId+",value="+token);
			jedis = getJedis();
			jedis.select(0);
			jedis.setex(openId, expire, token);
			Long ttl = jedis.ttl(openId);
			log.info("token缓存成功！剩余时间="+ttl+"秒");
		} catch (Exception e) {
			returnBrokenResource(jedis);
			e.printStackTrace();
		}finally{
			returnResource(jedis);
		}
	}

	/**
	 * 根据key取值
	 * @param dbNum 数据库编号
	 */
	public static String getValue(String key, int dbNum){
		Jedis jedis = null;
		String value = "";
		if ("".equals(key) || key == null) {
			return value;
		}
		try {
			jedis = getJedis();
			jedis.select(dbNum);
			value = jedis.get(key);
			return value;
		} catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}

	/**
	 * 根据key获取值 默认0库
	 */
	public static String getValue(String key) {
		return getValue(key,0);
	}

	/**
	 * 删除指定key(指定库)
	 */
	public static long del(String key,int dbNum){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(dbNum);
			return jedis.del(key);
		}catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}
	/**
	 * 删除指定key(指定库)
	 */
	public static long del(String key){
		return del(key,0);
	}

	/**
	 * 为给定key设置生存时间。当key过期时，它会被自动删除
	 * @param seconds 有效时间（秒）
	 */
	public static long expire(String key, int seconds,int dbNum){
        Jedis jedis = null;
		try {
			jedis= getJedis();
			jedis.select(dbNum);
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}
	public static long expire(String key, int expire){
		return expire(key,expire,0);
	}
	/**
	 *	查询key剩余时间
	 *@return 秒
	 */
	public static long ttl(String key,int dbNum){
        Jedis jedis = null;
		if("".equals(key) || key==null) return -1;
		try {
			jedis=getJedis();
			jedis.select(dbNum);
			return jedis.ttl(key);
		} catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}
	public static long ttl(String key){
		return ttl(key,0);
	}
	/**
	 * 判断键是否存在，不存在返回false
	 */
	public static boolean exist(String key,int dbNum) {
		Jedis jedis = null;
		if ("".equals(key) || key == null) {
			return false;
		}
		try {
			jedis = getJedis();
			jedis.select(dbNum);
			return jedis.exists(key.getBytes());
		} catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}
	public static boolean exist(String key){
		return exist(key,0);
	}

	/**
	 * 修改键值
	 */
	public static String rename(String oldKey,String newKey,int dbNum){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.select(dbNum);
			return jedis.rename(oldKey,newKey);
		}catch (Exception e) {
			returnBrokenResource(jedis);
			throw new RuntimeException(e);
		}finally{
			returnResource(jedis);
		}
	}
	public static String rename(String oldKey,String newKey){
		return rename(oldKey,newKey,0);
	}

}
