package com.MusicBrainProject;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestService {

    public List<String> getRandomWords(int amount) throws Exception {
        //as every entry is needed to be unique set will be the best data structure
        Set<String> wordSet = new HashSet<>();
        //loop until we have enough elements in set
        while (wordSet.size() != amount) {
            HttpResponse<String> response = ResponseCreator.getResponse("https://random-words-api.vercel.app/word");
            JsonObject JsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            wordSet.add(JsonResponse.get("word").getAsString());
        }
        //parse set to list
        return wordSet.stream().collect(Collectors.toList());
    }
    
}
