package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {

    private static Logger serverLogger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {

        serverLogger.info("Starting logger...");

        SpringApplication.run(Server.class, args);

    }
}


//TODO: utz' design implementieren
//TODO: javadoc/signatures