package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String port;
    private final String memoryLimit;
    private final String cfInstanceIndex;
    private final String cfInstanceAddress;

    private Map<String, String> myEnv = new HashMap<>();

    public EnvController(
            @Value("${port:NOT SET}") String port,
            @Value("${memory.limit:NOT SET}") String memoryLimit,
            @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
            @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddress
    ) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddress = cfInstanceAddress;

        this.myEnv.put("PORT", port);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);

        return env;
    }

//    public static Map<String, String> env = new HashMap<>();
//
//    public EnvController(@Value("${env.port:NOT SET}")String port,
//                         @Value("${env.memoryLimit:NOT SET}")String memoryLimit,
//                         @Value("${env.cfInstanceIndex:NOT SET}")String cfInstanceIndex,
//                         @Value("${env.cfInstanceAddress:NOT SET}")String cfInstanceAddress) {
//
//        env.put("PORT", port);
//        env.put("MEMORY_LIMIT", memoryLimit);
//        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
//        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
//
//    }
//
//    public Map<String, String> getEnv() {
//        return env;
//    }
//
//    public void setEnv(Map<String, String> env) {
//        env = env;
//    }
}
