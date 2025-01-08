package com.nodirverse.albatros.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FileStorageProperties {
    @Value("${file.upload-dir}")
    private String uploadDir;

}

