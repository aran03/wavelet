import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    ArrayList<String> searchTerms;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Output:");
        } 
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("s=");
            searchTerms.add(parameters[1]);  
            return "Added item!";            
        }
        else if (url.getPath().contains("/search")) {
            String[] parametersS = url.getQuery().split("s=");
            if(searchTerms.contains(parametersS[1]))
                return "contained!";
            else {
                return "not found!";
            }
        }
        else {
            return "404 Not Found!";
        }
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
