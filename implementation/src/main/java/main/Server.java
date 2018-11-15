package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {

  private static Logger serverLogger = LoggerFactory.getLogger(Server.class);

  public static void main(String[] args)
      throws IOException, TranscoderException {

    serverLogger.info("Starting logger...");
    serverLogger.info(print(args));

    SpringApplication.run(Server.class, args);


  }

  private static String print(String[] args) {
    if (args.length > 0) {
      for (String s : args) {
        return (s + '\n');
      }
    }
    return "No arguments have been passed.";
  }
}

//TODO: Utz: REST API Documentation
//TODO: Xandi: Show difference between designs
//TODO: Xandi: Javadoc in HTML extrahieren
//TODO: Xandi: Code metrics of current implementation
//TODO: Daniel: Discuss how we applied observer and strategy patterns
