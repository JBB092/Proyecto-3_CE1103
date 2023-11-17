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

public class RESTAPI implements Runnable{

    public RESTAPI() {
        Thread escucho = new Thread(this);
        escucho.start();
    }
    @Override
    public void run() {
        try {
            HttpServer apiServer = HttpServer.create(new InetSocketAddress(9999),128);
            System.out.println("Servidor abierto");
            apiServer.createContext("/",new SimpleHandler());
            apiServer.setExecutor(null);
            apiServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class SimpleHandler implements HttpHandler

{
    private JSONObject getJsonBody(InputStream body){
        Scanner s = new Scanner(body).useDelimiter("\\A");
        String jsonImput = s.hasNext() ? s.next() : "";
        s.close();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(jsonImput);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
    private JSONObject handlePOST(JSONObject myJson) throws InterruptedException {
        System.out.println("handle metodo aceptado");
        JSONObject respuesta = new JSONObject();
        String metodo = (String) myJson.get("tipoConsulta");
        if(metodo.equals("login")){
            //implement
        }
        else if(metodo.equals("register")){
            //IMPLEMENT
        }
        return respuesta;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
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

            exchange.sendResponseHeaders(200, responseJson.toJSONString().getBytes().length);


            OutputStream output = exchange.getResponseBody();
            output.write(responseJson.toJSONString().getBytes());
            output.flush();


        } else if (("GET".equals(exchange.getRequestMethod()))) {
            //METODOS PARA GET
        } else {
            //CUALQUIER OTRO METODO NO SE CONTEMPLA A MENOS DE QUE SE ESPECIFIQUE
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();
    }
}
