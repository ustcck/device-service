package com.lenovo.cloud.device.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.lenovo.cloud.device.repository")
public class DeviceServiceConfiguration {

}
