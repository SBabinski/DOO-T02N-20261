package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    static Dotenv dotenv = Dotenv.load();
    static String apiKey = dotenv.get("MINHA_API_KEY");
    static String endpoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

    

        try {
            System.out.println("Digite o nome da cidade");
            String cidade = scan.nextLine();
            String cidade_trim = cidade.replace(" ", "");

            String requisicao = String.format("%s%s?unitGroup=metric&lang=pt&key=%s", endpoint, cidade_trim, apiKey);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requisicao)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode json = mapper.readTree(response.body());

            JsonNode days = json.get("days");
            JsonNode primeiroDia = days.get(0);

            double temperatura = json.get("currentConditions").get("temp").asDouble();
            double tempMin = primeiroDia.get("tempmin").asDouble();
            double tempMax = primeiroDia.get("tempmax").asDouble();
            double humidade = json.get("currentConditions").get("humidity").asDouble();
            String condicao = json.get("currentConditions").get("conditions").asText(); 
            double precipitacao = json.get("currentConditions").get("precip").asDouble();
            double velocidadeVento = json.get("currentConditions").get("windspeed").asDouble();
            double direcaoVento = json.get("currentConditions").get("winddir").asDouble();

            System.out.println("Cidade: " + cidade);
            System.out.println("Temperatura: " + temperatura + "°C");
            System.out.println("Temperatura mínima: " + tempMin + "°C");
            System.out.println("Temperatura máxima: " + tempMax + "°C");
            System.out.println("Humidade: " + humidade);
            System.out.println("Condição: " + condicao);
            System.out.println("Precipitação: " + precipitacao);
            System.out.println("Velocidade do vento: " + velocidadeVento + " Km/h");
            System.out.println("Direção do vento: " + direcaoVento + "°");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
    }

}