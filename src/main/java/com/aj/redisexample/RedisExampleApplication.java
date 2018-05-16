package com.aj.redisexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class RedisExampleApplication {

    private RedisRepo redisRepo;

	public static void main(String[] args) {
		SpringApplication.run(RedisExampleApplication.class, args);
	}

	@GetMapping({"","/"})
    @ResponseBody Object home(){
	    redisRepo.deleteAll();
        System.out.println("Hello");
        return "Hello";
    }

    @GetMapping("/all")
    @ResponseBody Object all(){
        return redisRepo.findAll();
    }

    @GetMapping("/key/{key}")
    @ResponseBody Object all(@PathVariable String key){
        return redisRepo.findById(key);
    }

    @GetMapping("/post/{key}/{value}")
    @ResponseBody Object post(@PathVariable String key, @PathVariable String value){
        redisRepo.save(key, value);
        return redisRepo.findAll();
    }

    @GetMapping("db")
    @ResponseBody Object db(){
        return new Repo().getNumber();
    }

    @GetMapping("redis")
    @ResponseBody Object redis(){
        return redisRepo.getNumber();
    }

    @GetMapping("/pop")
    @ResponseBody Object populate(){
        List<Question> questions = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Question question = new Question();
            question.setQuestion("Question");
            question.setAnswer("Answer");
            questions.add(question);
        }
        redisRepo.save("obj",questions);
        return redisRepo.getNumber();
    }

    @GetMapping("/q")
    @ResponseBody Object q(){
        return redisRepo.findQuestionById("obj");
    }

    public RedisRepo getRedisRepo() {
        return redisRepo;
    }

    @Autowired
    public void setRedisRepo(RedisRepo redisRepo) {
        this.redisRepo = redisRepo;
    }
}
