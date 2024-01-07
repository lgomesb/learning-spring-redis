package pt.com.learning.spring.redis.learningspringredis;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import redis.clients.jedis.Jedis;



@SpringBootApplication
public class LearningSpringRedisApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(LearningSpringRedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("redis-stack");
		System.out.println("Connected to Redis");

		String key = UUID.randomUUID().toString();
        // System.out.println("Valor: " + key);
		// String value = "Hello Redis";
		// jedis.set(key, value);
		// String result = jedis.get(key);
		Map<String,String> map = new HashMap<>();
		map.put("firstName", "Fulano");
		map.put("lastname", "Silva");
		map.put("age", "45");
		map.put("gender", "MALE");
		String result = jedis.hmset(key, map);
        System.out.println("Valor: " + result);

		jedis.close();
	}

}
