package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;
//import org.json.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Server implements Runnable {
    protected ServerSocket serverSocket;
    protected Socket sSocket;
    protected Socket enviarRespuesta;
    protected DataInputStream entrada;
    protected DataOutputStream envio;
    //    protected Request request;
    protected FileWriter writer;
    public static void main(String[] args) {
        Server server= new  Server();
        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();

            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, "users", null);
            documento.setXmlVersion("1.0");

            // Creo los elementos
            Element users = documento.createElement("users");
            Element user = documento.createElement("user");

            // Carnet
            Element carnet = documento.createElement("carnet");
            Text textCarnet = documento.createTextNode("1234");
            carnet.appendChild(textCarnet);
            user.appendChild(carnet);

            // Nombre
            Element nombre = documento.createElement("nombre");
            Text textNombre = documento.createTextNode("Ale");
            nombre.appendChild(textNombre);
            user.appendChild(nombre);

            // Residencia
            Element residencia = documento.createElement("residencia");
            Text textRecidencia = documento.createTextNode("Cartago");
            residencia.appendChild(textRecidencia);
            user.appendChild(residencia);

            // Conductor o no
            Element isDriver = documento.createElement("isDriver");
            Text textIsDriver = documento.createTextNode("false");
            isDriver.appendChild(textIsDriver);
            user.appendChild(isDriver);

            // Añado al elemento coches el elemento coche
            users.appendChild(user);

            // Añado al root el elemento coches
            documento.getDocumentElement().appendChild(users);

            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File("src//users.xml"));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public Server() {
        try {
            serverSocket=new ServerSocket(9999);
            sSocket =serverSocket.accept();
            entrada=new DataInputStream(sSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(this);
        thread.start();
    }
    public void registration(){

    }
    public void newUser(){

    }
    public void loggin(){

    }
    public boolean checkUser(){
        return false;
    }
    @Override
    public void run() {
        try {
            String jsonString = entrada.readUTF();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
