package my.flipside.application.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean(autowire = Autowire.BY_NAME)
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("my.flipside");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-247-79-32.eu-west-1.compute.amazonaws.com:5432/ddbqh5qho6v4gc?ssl=true&amp;sslfactory=org.postgresql.ssl.NonValidatingFactory");
        dataSource.setUsername("ndbekguuylikcy");
        dataSource.setPassword("60bb7ec5c671d2fea3692926039cb38c54d7ab2ff7c872d6f9dd7b65ff77e110");
        dataSource.addConnectionProperty("ssl", "true");
        dataSource.addConnectionProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setMaxActive(100);
        dataSource.setMaxIdle(20);
        dataSource.setMaxWait(60);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty(
                "show_sql", "true");

        return hibernateProperties;
    }
}
