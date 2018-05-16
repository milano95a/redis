package com.aj.redisexample;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Repo {

    public int getNumber(){
        sleep();
        return new Random().nextInt();
    }

    private static void sleep(){
        try{
            Thread.currentThread().sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
