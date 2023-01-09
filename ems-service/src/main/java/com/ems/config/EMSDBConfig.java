package com.ems.config;

import static java.util.Collections.singletonMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.ems.util.AppConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "emsEntityManager", transactionManagerRef = "emsTransactionManager", basePackages = {
		"com.ems" })
public class EMSDBConfig {

	public static final String EMS_DATA_SOURCE = "ems_data_source";
	public static final String EMS_ENTITY_MANAGER = "emsEntityManager";
	public static final String EMS_DB_TRANSACTION_MANAGER = "emsTransactionManager";

	@Bean(name = EMS_DATA_SOURCE)
	public DataSource emsDatasource() {
		// DataSourceBuilder<DataSource> DataSourceBuilder = DataSourceBuilder.create();
		try {
			Map<String, Object> emsDBConfig = AppConfig.getInstance().getJsonMapConfigNoCached("mysql_database.json");

			Map<String, Object> emsConfig = (Map<String, Object>) emsDBConfig.get("ems_h2_db");

			HikariConfig config = new HikariConfig();
			config.setJdbcUrl(emsConfig.get("url").toString());
			config.setUsername(emsConfig.get("username").toString());
			config.setPassword(emsConfig.get("password").toString());
			config.setDriverClassName(emsConfig.get("driverClassName").toString());
			config.setAutoCommit(Boolean.parseBoolean(emsConfig.get("connectionAutoCommit").toString()));
			config.setMaximumPoolSize(Integer.parseInt(emsConfig.get("connectionPoolMaxSize").toString()));
			config.setMinimumIdle(Integer.parseInt(emsConfig.get("connectionPoolIdleSize").toString()));
			config.setMaxLifetime(Integer.parseInt(emsConfig.get("connectionPoolMaxLisfetimeMs").toString()));
			config.setConnectionTimeout(Integer.parseInt(emsConfig.get("connectionPoolTimeoutMs").toString()));
			config.setLeakDetectionThreshold(
					Integer.parseInt(emsConfig.get("connectionLeakedDetectionThreasholdMs").toString()));

			return new HikariDataSource(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	Map<String, ?> additionalJpaProperties() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		map.put("hibernate.show_sql", "true");

		return map;
	}

	@Bean(name = EMS_ENTITY_MANAGER)
	public LocalContainerEntityManagerFactoryBean emsEntityManagerFactory(final EntityManagerFactoryBuilder builder,
			final @Qualifier(EMS_DATA_SOURCE) DataSource datasource) {

		return builder.dataSource(datasource).packages("com.ems").persistenceUnit("ems_h2_db")
				.properties(singletonMap("hibernate.naming.physical-strategy",
						"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl"))
				.build();
	}

	@Bean(name = EMS_DB_TRANSACTION_MANAGER)
	public DataSourceTransactionManager transactionManager(@Qualifier(EMS_DATA_SOURCE) DataSource datasource) {
		DataSourceTransactionManager emsTransactionManager = new DataSourceTransactionManager(datasource);
		return emsTransactionManager;
	}
}
