/*
package my.flipside.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf
                = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("my.flipside");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(additionalProperties());

        return emf;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-247-79-32.eu-west-1.compute.amazonaws.com:5432/ddbqh5qho6v4gc?ssl=true&amp;sslfactory=org.postgresql.ssl.NonValidatingFactory");
        dataSource.setUsername("ndbekguuylikcy");
        dataSource.setPassword("60bb7ec5c671d2fea3692926039cb38c54d7ab2ff7c872d6f9dd7b65ff77e110");
        //dataSource.addConnectionProperty("ssl", "true");
        //dataSource.addConnectionProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        //dataSource.setDefaultAutoCommit(false);
        //dataSource.setMaxActive(100);
        //dataSource.setMaxIdle(20);
        //dataSource.setMaxWait(60);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("show_sql", "true");

        return properties;
    }
}
*/
