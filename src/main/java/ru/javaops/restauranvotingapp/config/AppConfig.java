package ru.javaops.restauranvotingapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Slf4j
public class AppConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile("!test")
    Server h2Server() throws SQLException {
        log.info("Start H2 TCP server");
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}