package com.quick.rest.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Package configuration Properties
 *
 * @author Paul Quintero
 * @enterprise Quick
 * @since 18/10/2022
 */
@Data
@Configuration
public class PackageProperties {
    @Value("{spring.application.name}")
    private String projectName;
    @Value("${quick.api.parent.basePackage}")
    private String basePackage;
    @Value("${quick.api.package.controller}")
    private String controller;
    @Value("${quick.api.package.entity}")
    private String entity;
    @Value("${quick.api.package.service}")
    private String service;
    @Value("${quick.api.package.repository}")
    private String repository;
}
