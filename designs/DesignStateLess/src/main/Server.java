package main;

/**
 * This class is the main class. The program starts here. The server runs and waits for REST requests.
 * All Requests will get redirected to the {@link RestHandler.RestHandler}.
 */
public class Server {

    /**
     * The main method is where the program starts.
     * @param args Can take parameters when the user starts the program.
     */
    public static void main(String[] args){
        System.out.println("hello world!");
    }
}

//TODO: shape implements sowohl moveBehaviour als auch subject?
//TODO: observer und update methode noch nicht ganz verstanden
//TODO: was macht cantmove