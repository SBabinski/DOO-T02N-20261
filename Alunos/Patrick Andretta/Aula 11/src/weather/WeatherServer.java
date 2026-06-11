package weather;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class WeatherServer {

    private final String apiKey;
    private final int port;

    public WeatherServer(String apiKey, int port) {
        this.apiKey = apiKey;
        this.port   = port;
    }

    public void start() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/weather", new WeatherApiHandler(apiKey));
        server.createContext("/", new StaticFileHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Servidor iniciado em http://localhost:" + port);
    }
}
