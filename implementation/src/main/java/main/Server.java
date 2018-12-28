package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Server is the launching class for the Spring Boot application.
 */
@SpringBootApplication
public class Server {

  private static Logger serverLogger = LoggerFactory.getLogger(Server.class);


  /**
   * Launches the server application via SpringApplication.run.
   *
   * @param args Command line arguments passed at the start of the program.
   * @see main.input.ProjectService
   * @see main.input.RESTHandler
   */
  public static void main(String[] args) {

    System.out.println("Composite & iterator test");
    serverLogger.info("Starting logger...");
    serverLogger.info(print(args));

    SpringApplication.run(Server.class, args);
  }

  /**
   * Returns initial command line arguments.
   * @param args Command line arguments passed at the start of the program.
   * @return An ordererd view of args
   */
  private static String print(String[] args) {
    if (args.length > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      for (String s : args) {
        stringBuilder.append(s).append('\n');
      }
      return stringBuilder.toString();
    }
    return "No arguments have been passed.";
  }
}

//TODO: Xandi: Code metrics of current implementation
