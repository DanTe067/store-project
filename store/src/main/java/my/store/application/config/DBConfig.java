package my.store.application.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@PropertySource("classpath:db/db.properties")
public class DBConfig {

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(@Autowired BasicDataSource basicDataSource) {
        return new NamedParameterJdbcTemplate(basicDataSource);
    }

    @Bean
    public BasicDataSource dataSource(@Autowired Environment environment) {
        BasicDataSource baseDataSource = new BasicDataSource();
        baseDataSource.setUrl(environment.getProperty("db.url"));
        baseDataSource.setUsername(environment.getProperty("db.username"));
        baseDataSource.setPassword(environment.getProperty("db.password"));
        baseDataSource.addConnectionProperty("ssl", "db.ssl");
        baseDataSource.addConnectionProperty("sslfactory", "db.sslfactory");
        baseDataSource.setDriverClassName(environment.getProperty("db.driver"));

        return baseDataSource;
    }
}
