package pl.zajavka.infrastucture.configuration;

import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    //dostęp do bazy danych
    @Bean
    public SimpleDriverDataSource simpleDriverDataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:postgresql://localhost:5432/zajavka_store");
        dataSource.setUsername("postgres");
        dataSource.setPassword("filip");
        return dataSource;
    }

    //bean do zarzadzanie transkakcjami
    @Bean
    public PlatformTransactionManager txManager(){
        return new DataSourceTransactionManager(simpleDriverDataSource());
    }

}
