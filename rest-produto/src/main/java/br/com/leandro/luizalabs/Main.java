/*
*
*/

package br.com.leandro.luizalabs;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class
 */
public class Main{    
    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig().packages("br.com.leandro.luizalabs");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/labs/rest/"), resourceConfig);
        System.out.println("Servidor rodando....");
        System.in.read();
        server.stop();
    }
}

