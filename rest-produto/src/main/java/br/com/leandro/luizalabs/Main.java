/*
 *
 */
package br.com.leandro.luizalabs;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import javax.servlet.ServletRegistration;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost").port(8080).path("/").build();
    }

    public static final URI BASE_URI = getBaseURI();

    public static void main(String[] args) throws IOException {
        ResourceConfig config = new ResourceConfig().packages("br.com.leandro.luizalabs");
        HttpServer httpServer = GrizzlyServerFactory.createHttpServer(BASE_URI, new HttpHandler() {

            @Override
            public void service(Request rqst, Response rspns) throws Exception {
                rspns.setStatus(404, "Not found");
                rspns.getWriter().write("404: not found");
            }
        });

        WebappContext context = new WebappContext("WebappContext", "/labs");
        ServletRegistration registration = context.addServlet("ServletContainer",
                new ServletContainer(config));
        registration.addMapping("/*");
        context.deploy(httpServer);
        
        System.out.println("Servidor rodando....");
        System.in.read();
        httpServer.stop();
    }

}
