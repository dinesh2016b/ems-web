package com.ems.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppConfig {

	private static AppConfig instance = null;

	private AppConfig() {
	}

	public static AppConfig getInstance() {
		if (instance == null) {
			instance = new AppConfig();
		}
		return instance;
	}

	public Map<String, Object> getJsonMapConfigNoCached(String fileName) throws IOException {

		Map<String, Object> jsonMap = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonMap = objectMapper.readValue(new FileInputStream(fileName), new TypeReference<Map<String, Object>>() {
			});
			for (Map.Entry<?, ?> entry : jsonMap.entrySet()) {
				log.info("----------> " + entry.getKey() + "=" + entry.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonMap;
	}

	/*
	 * public static void main(String[] args) { try {
	 * AppConfig.getInstance().getJsonMapConfigNoCached("mysql_database.json"); }
	 * catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

}
