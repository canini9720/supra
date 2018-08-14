package com.supra.common;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.test.context.ActiveProfiles;


//@Configuration
//@ActiveProfiles("test")
//@Profile("test")
public class CommonTestConfig {

	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521/XE");
        dataSource.setUsername("Test");
        dataSource.setPassword("root");
 
        return dataSource;
    }
	
	/*@Bean(destroyMethod = "")
    public DataSource dataSource() {
        return (DataSource) new JndiObjectFactoryBean() {{
            setExpectedType(DataSource.class);
            setResourceRef(true);
            setJndiName("jdbc/TESTService");
            try {
                afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}.getObject();
    }*/
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.testservice", "com.testservice.model"});
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

        hibernateProperties.setProperty("hibernate.default_schema", "TEST");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
}