package com.MusicBrainProject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseCreator {

    public static HttpResponse getResponse(String uri) throws InterruptedException, IOException {
        //creating new HTTP GET request to the given uri
        HttpRequest request = HttpRequest.newBuilder()
                .header("Accept","application/json")
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        //sending request and getting response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
