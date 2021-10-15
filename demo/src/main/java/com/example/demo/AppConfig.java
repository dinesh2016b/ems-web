package com.example.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class AppConfig implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}

	@Override
	public void run(String[] args) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			Map<?, ?> map = objectMapper.readValue(new FileInputStream("mysql_database.json"), Map.class);
			
			for (Map.Entry<?, ?> entry : map.entrySet()) {
			//	System.out.println(entry.getKey() + "=" + entry.getValue());
			}
			
			Map<String, Object> map1
			  = objectMapper.readValue(new FileInputStream("mysql_database.json"), new TypeReference<Map<String,Object>>(){});
			for (Map.Entry<?, ?> entry : map1.entrySet()) {
				System.out.println(entry.getKey() + "=" + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
