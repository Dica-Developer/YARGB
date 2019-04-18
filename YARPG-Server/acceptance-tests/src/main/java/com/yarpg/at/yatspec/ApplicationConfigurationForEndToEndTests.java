package com.yarpg.at.yatspec;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
// @ComponentScan(basePackages = {
// "com.yarpg.apllication.configuration" }, excludeFilters = @ComponentScan.Filter(value =
// JobSchedulerConfiguration.class, type = FilterType.ASSIGNABLE_TYPE))
@ComponentScan(basePackages = { "com.yarpg.application.configuration" })
public class ApplicationConfigurationForEndToEndTests {
}
