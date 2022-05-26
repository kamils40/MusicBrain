package com.MusicBrainProject;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class RequestService {

    public List<String> getRandomWords(int amount)  {
        //as every entry is needed to be unique set will be the best data structure
        Set<String> wordSet = new HashSet<>();
        //loop until we have enough elements in set
        while (wordSet.size() != amount) {
            //send request to the uri and get a response
            try {
                HttpResponse<String> response = ResponseCreator.getResponse("https://random-words-api.vercel.app/word");
                wordSet.add(JsonHandler.parseWord(response));
            } catch (InterruptedException | IOException e) {
                System.out.println("Request interrupted. Trying again");
            }
        }
        //parse set to list
        return wordSet.stream().collect(Collectors.toList());
    }
    public List<Result> getSongInformation(int amount) {
        List<String> randomWords = getRandomWords(amount);
        System.out.println("Words size:" + randomWords.size());
        List<Result> results = new ArrayList<>();
        for (String word : randomWords) {
            try {
                HttpResponse<String> response = ResponseCreator.getResponse(
                        String.format("http://musicbrainz.org/ws/2/recording?query=%s&limit=1", word));
                JsonObject JsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
                //Sleep there for 1 second because we can overload API as average request per second is 1
                Thread.sleep(1000);
                //count  shows us how many songs query found, if its 0 than no song found
                if (JsonResponse.get("count").getAsInt() == 0) {
                   results.add(new Result(word, "No recording found!"));
                } else {
                    SongInformation song = JsonHandler.parseSong(JsonResponse);
                    //check if result actually has the word in title as API can return wrong titles
                    if (song.getRecordingTitle().toLowerCase().contains(word.toLowerCase())) {
                       results.add(new Result(word, song.toString()));
                    } else {
                        results.add(new Result(word, "No recording found!"));
                    }
                }
            } catch (InterruptedException | IOException e) {
                results.add(new Result(word,"failed getting response from API"));
            }
        }
        return results;
    }
}
