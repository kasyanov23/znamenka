package ru.click.reporting.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ComponentScan("ru.click.reporting.*")
public class ReportingAutoConfiguration {

}
