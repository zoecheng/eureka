package com.csy.spgcld.servicehi2;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class ServiceHi2Application {
    private static final Logger LOG = Logger.getLogger(ServiceHi2Application.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ServiceHi2Application.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    @RequestMapping("/hi2")
    public String home(){
        LOG.log(Level.INFO, "hi2 is being called");
        return "hi i'm hi2!";
    }

    @RequestMapping("/info2")
    public String info(){
        LOG.log(Level.INFO, "info2 is being called");
        return restTemplate.getForObject("http://localhost:8762/info1",String.class);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}

