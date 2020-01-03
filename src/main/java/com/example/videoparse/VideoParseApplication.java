package com.example.videoparse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@MapperScan("com.example.videoparse.mapper")
@SpringBootApplication
public class VideoParseApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoParseApplication.class, args);
    }

}
