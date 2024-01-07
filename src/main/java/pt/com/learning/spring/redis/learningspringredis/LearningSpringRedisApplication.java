package pt.com.learning.spring.redis.learningspringredis;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@EnableCaching
@SpringBootApplication
public class LearningSpringRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringRedisApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(ProductService productService) {
		return args -> {
			System.out.println(System.lineSeparator());
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 2 " + productService.getById(2L));
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 1 " + productService.getById(1L));
			System.out.println("Id: 1 " + productService.getById(1L));
		};
	}
	record Product(Long id, String name, String description) implements Serializable {}

	@Service
	class ProductService {

		Map<Long, Product> products = new HashMap<>() {
			{
				put(1L, new Product(1L, "Laptop", "Macbook Air"));
				put(2L, new Product(2L, "Laptop", "HP"));
				put(3L, new Product(3L, "Laptop", "XPS"));
				put(4L, new Product(4L, "Laptop", "Thinkpad"));
				put(5L, new Product(5L, "Laptop", "Chromebook"));
			}
		};

		@Cacheable("products")
		Product getById(Long id) {
			System.out.println("Getting products ...");
			latencySimulation();
			return products.get(id);
		}

		private void latencySimulation() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}
}
