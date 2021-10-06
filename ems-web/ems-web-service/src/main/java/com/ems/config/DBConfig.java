package com.ems.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "emsEntityManager", 
						transactionManagerRef = "emsTransactionManager", 
						basePackages = {"com.ems" })
public class DBConfig {

	public static final String EMS_DATA_SOURCE = "ems_data_source";
	public static final String EMS_ENTITY_MANAGER = "emsEntityManager";
	public static final String EMS_DB_TRANSACTION_MANAGER = "emsTransactionManager";

	@Bean("emsDataSourceProperties")
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties emsDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = EMS_DATA_SOURCE)
	public DataSource emsDatasource(
			@Qualifier("emsDataSourceProperties") DataSourceProperties emsDataSourceProperties) {
		return emsDataSourceProperties.initializeDataSourceBuilder().build();
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

		return builder.dataSource(datasource).packages("com.ems").persistenceUnit("ems")
				.properties(additionalJpaProperties()).build();
	}

	@Bean(name = EMS_DB_TRANSACTION_MANAGER)
	public DataSourceTransactionManager transactionManager(@Qualifier(EMS_DATA_SOURCE) DataSource datasource) {
		DataSourceTransactionManager emsTransactionManager = new DataSourceTransactionManager(datasource);
		return emsTransactionManager;
	}
}
