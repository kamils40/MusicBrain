package com.MusicBrainProject;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RequestService {

    public List<String> getRandomWords(int amount) throws InterruptedException, IOException {
        //as every entry is needed to be unique set will be the best data structure
        Set<String> wordSet = new HashSet<>();
        //loop until we have enough elements in set
        while (wordSet.size() != amount) {
            //send request to the uri and get a response
            HttpResponse<String> response = ResponseCreator.getResponse("https://random-words-api.vercel.app/word");
            wordSet.add(JsonHandler.parseWord(response));
        }
        //parse set to list
        return wordSet.stream().collect(Collectors.toList());
    }
    public List<Result> getSongInformation(List<String> randomWords) {
        List<Result> results = new ArrayList<>();
        for (String word : randomWords) {
            try {
                HttpResponse<String> response = ResponseCreator.getResponse(
                        String.format("http://musicbrainz.org/ws/2/recording?query=%s&limit=1", word));
                JsonObject JsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                if (JsonResponse.get("count").getAsInt() == 0) {
                    results.add(new Result(word,"No recording found!"));
                } else {
                    SongInformation song = JsonHandler.parseSong(JsonResponse);
                   results.add(new Result(word, song.toString()));
                }
            } catch (InterruptedException | IOException e) {
                System.out.println(e.toString());
                //results.add(new Result(word,"failed getting response from API"));
            }
        }
        return results;
    }
}
