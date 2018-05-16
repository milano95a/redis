package com.aj.redisexample;

import java.util.Random;

public class Worker extends Thread {

    String num;

    RedisRepo redisRepo;

    public Worker(String num, RedisRepo redisRepo){
        this.redisRepo = redisRepo;
        this.num = num;
    }


    @Override
    public void run() {
        redisRepo.save(num,new Random().nextInt() + " thread created number");
    }
}
