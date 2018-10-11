package com.dreaming.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * @author lucky
 * create on 2017/12/9
 */
@PropertySource("classpath:properties/local.properties")
public class RedisUtil {
    @Value("${redis.maxIdle}")
    private static Integer minIdle;

    @Value("${redis.maxIdle}")
    private static Integer maxIdle;

    @Value("${redis.maxTotal}")
    private static Integer maxTotal;

    @Value("${redis.maxWait}")
    private static Integer maxWaitMillis;

    @Value("${redis.testOnBorrow}")
    private static boolean testOnBorrow;

    @Value("${spring.redis.cluster.nodes}")
    private static String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private static Integer mmaxRedirectsac;

    private static JedisCluster jedisCluster;

    static  {
        String[] cNodes = clusterNodes.split(",");
        Set<HostAndPort> nodes =new HashSet<>();
        //分割出集群节点
        for(String node : cNodes) {
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
        }
        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMinIdle(minIdle);
        //创建集群对象
        jedisCluster = new JedisCluster(nodes,jedisPoolConfig);
    }

    /**
     * 存取字符型key与value
     * @param key
     * @param value
     */
    public static void put(String key,String value){
        jedisCluster.set(key,value);
    }

    /**
     * 存取list
     * @param key
     * @param list
     */
    public static void put(String key,List<String> list){
        String[] values = (String[]) list.toArray();
        jedisCluster.lpush(key,values);
    }

    /**
     * 存取数组
     * @param key
     * @param list
     */
    public static void put(String key,String... list){
        jedisCluster.lpush(key,list);
    }

    public static void put(String key,HashMap<String,String> map){
        for(Map.Entry<String,String> entry: map.entrySet()){
            jedisCluster.hset(key,entry.getKey(),entry.getValue());
        }
    }

}
