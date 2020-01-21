package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    private String welcomeMsg;


    public WelcomeController(@Value("${welcome.message}") String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

//    @GetMapping("/hello")
//    public String sayHello() {
//        return "hello";
//    }
//
//    @GetMapping("/bye")
//    public String sayBye() {
//        return "bye";
//    }

    @GetMapping("/")
    public String sayHello() {
        return getWelcomeMsg();
    }


}
