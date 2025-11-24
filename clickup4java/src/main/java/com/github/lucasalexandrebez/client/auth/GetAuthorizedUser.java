package com.github.lucasalexandrebez.client.auth;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class GetAuthorizedUser {

    /**
     * O que temos em comum? 
     * 
     * HttpClient
     * HttpRequest
     * Parte da URL
     * Token de autenticação(Personal ou OAuth)
     * 
     * O que pode ser melhorado?
     * URLS podem ficar em um tipo de lista facil de se fazer a manutencao(Enum talvez?)
     * Devo dar opções para como a pessoa ira adicionar o seu token de acesso seja direta ou indiretamente
     * Devo criar annotations?
     * Devo crair variaveis para .properties?
     * Devo retornar ou um objeto convertido com os models ou uma String ou um JSON ou seja o usuario decide o que usar
     */

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://foo.com/"))
            .header(null, null)
            .GET()
            .expectContinue(false)
            .method(null, null)
            .build();
        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join(); 
    }
}
