package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@Configuration
public class PalTrackerApplication {

//    TimeEntryRepository timeEntryRepo = new InMemoryTimeEntryRepository();

    public static void main (String[] args){
        SpringApplication.run(PalTrackerApplication.class, args);

    }

   @Bean
    public TimeEntryRepository timeEntryRepository(){
       MysqlDataSource dataSource = new MysqlDataSource();
       dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));

        return new JdbcTimeEntryRepository(dataSource);
    }

}
