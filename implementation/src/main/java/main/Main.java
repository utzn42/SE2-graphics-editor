package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Starting logger...");

        SpringApplication.run(Main.class, args);

    }
}


//TODO: daniels design implementieren
//TODO: utz' design implementieren
//TODO: javadoc/signatures
//TODO: REST Kommunikation