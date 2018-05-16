package com.aj.redisexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import java.util.*;

@Repository
public class RedisRepo {

    @Inject
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    Repo repository;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object findById(String key) {

        if(redisTemplate.hasKey(key)){
            return redisTemplate.opsForValue().get(key);
        }

        return null;
    }

    public Object findQuestionById(String key) {

        if(redisTemplate.hasKey(key)){
            return redisTemplate.opsForValue().get(key);
        }

        return null;
    }

    public HashMap<String,Object> findAll() {

        HashMap<String,Object> hashMap = new HashMap<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            Object value = findById(key);
            hashMap.put(key,value);
        }

        return hashMap;
    }

    public void deleteAll(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

    public Object getNumber(){
        if(redisTemplate.hasKey("num")){
            Object obj = redisTemplate.opsForValue().get("num");
            Worker worker = new Worker("num",this);
            worker.start();
            return obj;
        }else{
            save("num",repository.getNumber() + "");
            return redisTemplate.opsForValue().get("num");
        }
    }
}
