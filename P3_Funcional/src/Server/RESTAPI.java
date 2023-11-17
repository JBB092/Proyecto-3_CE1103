package Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * RESTAPI class represents a simple HTTP server providing RESTful API functionality.
 *
 * <p>This class implements the Runnable interface to run the HTTP server in a separate thread.</p>
 *
 * <p>Author: Alejandro Solis</p>
 */
public class RESTAPI implements Runnable {

    /**
     * Constructor for the RESTAPI class.
     * Creates a new thread to run the HTTP server.
     */
    public RESTAPI() {
        Thread listenerThread = new Thread(this);
        listenerThread.start();
    }

    /**
     * Implementation of the run method from the Runnable interface.
     * Starts the HTTP server and sets up the request handler.
     */
    @Override
    public void run() {
        try {
            // Create HTTP server at port 9999
            HttpServer apiServer = HttpServer.create(new InetSocketAddress(9999), 128);
            System.out.println("Server opened");

            // Set up the context and request handler
            apiServer.createContext("/", new SimpleHandler());
            apiServer.setExecutor(null);
            apiServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * SimpleHandler class implements the HttpHandler interface to handle HTTP requests.
 *
 * <p>This class is responsible for parsing JSON requests and generating appropriate responses.</p>
 *
 * <p>Author: Alejandro Solis</p>
 */
class SimpleHandler implements HttpHandler {

    /**
     * Parses the JSON body from the input stream.
     *
     * @param body The input stream containing the JSON data.
     * @return A JSONObject representing the parsed JSON data.
     */
    private JSONObject getJsonBody(InputStream body) {
        Scanner scanner = new Scanner(body).useDelimiter("\\A");
        String jsonInput = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(jsonInput);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    /**
     * Handles POST requests and generates a response based on the JSON data received.
     *
     * @param myJson The JSON data received in the POST request.
     * @return A JSONObject representing the response to the POST request.
     * @throws InterruptedException If the handling process is interrupted.
     */
    private JSONObject handlePOST(JSONObject myJson) throws InterruptedException {
        System.out.println("handle method accepted");
        JSONObject response = new JSONObject();
        String method = (String) myJson.get("tipoConsulta");
        if (method.equals("login")) {
            // Implementation for login
        } else if (method.equals("register")) {
            // Implementation for registration
        }
        return response;
    }

    /**
     * Handles HTTP requests.
     *
     * @param exchange The HttpExchange object representing the HTTP request and response.
     * @throws IOException If an I/O error occurs during request handling.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("handle");
        if ("POST".equals(exchange.getRequestMethod())) {
            System.out.println("post");
            JSONObject myJson = getJsonBody(exchange.getRequestBody());

            JSONObject responseJson = null;
            try {
                System.out.println("Handle method");
                responseJson = handlePOST(myJson);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Send the response to the client
            exchange.sendResponseHeaders(200, responseJson.toJSONString().getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(responseJson.toJSONString().getBytes());
            output.flush();
        } else if (("GET".equals(exchange.getRequestMethod()))) {
            // Methods for handling GET requests
        } else {
            // Any other method is not allowed unless specified
            exchange.sendResponseHeaders(405, -1); // 405 Method Not Allowed
        }
        exchange.close();
    }
}