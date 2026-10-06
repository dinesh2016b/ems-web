package com.ems.config;

import com.ems.util.AppConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "emsEntityManager", transactionManagerRef = "emsTransactionManager", basePackages = {
		"com.ems" })
public class EMSDBConfig {
	public static final String EMS_DATA_SOURCE = "ems_data_source";
	public static final String EMS_JNDI_NAME_MYSQL = "java:/datasources/jdbc/emsdb";
	public static final String EMS_JNDI_NAME_H2 = "java:/datasources/jdbc/emsdb_h2";
	public static final String EMS_ENTITY_MANAGER = "emsEntityManager";
	public static final String EMS_DB_TRANSACTION_MANAGER = "emsTransactionManager";

    @Bean(name = EMS_DATA_SOURCE)
    DataSource emsDatasource() {
		//DataSourceBuilder<DataSource> DataSourceBuilder = DataSourceBuilder.create();
		try {

			Map<String, Object> emsDBConfig = AppConfig.getInstance().getJsonMapConfigNoCached("mysql_database.json");
			//Map<String, Object> emsConfig = (Map<String, Object>) emsDBConfig.get("ems_mysql_db");			
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

			//return (DataSource) new JndiTemplate().lookup(EMS_JNDI_NAME_H2);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
        //catch (IOException e) {e.printStackTrace();	}
		
		//return null;
	}

	Map<String, ?> additionalJpaProperties() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("hibernate.hbm2ddl.auto", "none");
		map.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		map.put("hibernate.show_sql", "true");

		return map;
	}

	@Bean
	DataSourceInitializer emsDataSourceInitializer(@Qualifier(EMS_DATA_SOURCE) DataSource datasource) {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("schema.sql"));
		populator.addScript(new ClassPathResource("data.sql"));

		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(datasource);
		initializer.setDatabasePopulator(populator);
		initializer.setEnabled(true);
		return initializer;
	}

    @Bean(name = EMS_ENTITY_MANAGER)
	LocalContainerEntityManagerFactoryBean emsEntityManagerFactory(final @Qualifier(EMS_DATA_SOURCE) DataSource datasource) {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	    factoryBean.setDataSource(datasource);
	    factoryBean.setPackagesToScan("com.ems");
	    factoryBean.setPersistenceUnitName("ems_h2_db");
	    factoryBean.setJpaVendorAdapter(vendorAdapter);
	    factoryBean.setJpaPropertyMap((Map) additionalJpaProperties());
	    factoryBean.getJpaPropertyMap().put("hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
	    return factoryBean;
	}

    @Bean(name = EMS_DB_TRANSACTION_MANAGER)
    @DependsOnDatabaseInitialization
    DataSourceTransactionManager transactionManager(@Qualifier(EMS_DATA_SOURCE) DataSource datasource) {
		DataSourceTransactionManager emsTransactionManager = new DataSourceTransactionManager(datasource);
		return emsTransactionManager;
	}
}
