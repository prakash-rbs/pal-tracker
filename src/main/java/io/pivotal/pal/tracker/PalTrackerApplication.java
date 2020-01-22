package io.pivotal.pal.tracker;

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
        return new InMemoryTimeEntryRepository();
    }

}
