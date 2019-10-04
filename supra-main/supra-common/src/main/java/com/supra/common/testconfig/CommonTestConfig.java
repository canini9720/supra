package com.supra.common.testconfig;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


//@Configuration
//@ComponentScan("com.supra") //disabled for junit test
public class CommonTestConfig {

	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("test");
        dataSource.setPassword("root");
        return dataSource;
		
        
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.supra.model", "com.supra.common.model"});
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.default_schema", "test");
       // hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
       hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
    
    @Bean
   	PropertyPlaceholderConfigurer propConfig() {
   		PropertyPlaceholderConfigurer ppc =  new PropertyPlaceholderConfigurer();
   		//ppc.setLocation(new ClassPathResource("dpservices.properties"));
   		//ppc.setLocation(new ClassPathResource("D:\\app\\dpservices.properties"));
   		ppc.setLocation(new FileSystemResource("D:\\app\\app.properties"));
   		return ppc;
   	}
    
    
}