package com.qa.choonz;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Random;

@SpringBootApplication
public class ChoonzApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ChoonzApplication.class, args);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        Random random = new Random();
        Integer randomInt = random.nextInt(10000);
        Server server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", randomInt.toString());
        System.out.println("TCP Server Port=" + server.getPort());
        return server;
    }
}
