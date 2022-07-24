package com.sheduler.sheduler.services;


import com.google.gson.Gson;
import com.sheduler.sheduler.model.AuthUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisUtility {
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    Gson gson;

    public void setValue(final String key, AuthUserDto authUserDto){
        redisTemplate.opsForValue().set(key, gson.toJson(authUserDto));
        redisTemplate.expire(key, 10, TimeUnit.MINUTES);
    }

    public AuthUserDto getValue(final String key){
        AuthUserDto authUserDto = null;
        try{
             authUserDto = gson.fromJson(redisTemplate.opsForValue().get(key), AuthUserDto.class);
        }catch (Exception e){
            log.info("Exceltion in getRdisValue::", e);
        }
        return authUserDto;
    }
}
