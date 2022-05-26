package com.MusicBrainProject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;

public class JsonHandler {

    public static String parseWord(HttpResponse<String> response) {
        // as our response from API is a JsonArray we need to work around it
        JsonArray JsonResponse = JsonParser.parseString(response.body()).getAsJsonArray();
        return JsonResponse.get(0).getAsJsonObject().get("word").getAsString();
    }
    public static SongInformation parseSong(JsonObject JsonResponse) {
            JsonArray recording = JsonResponse.getAsJsonArray("recordings");
            String title = "";
            String artists = "";
            String release = "";
            for (JsonElement record : recording) {
                title = record.getAsJsonObject().get("title").getAsString();
                StringBuilder sb = new StringBuilder();
                JsonArray artistsArray = record.getAsJsonObject().get("artist-credit").getAsJsonArray();
                for (JsonElement artist : artistsArray) {
                    sb.append(artist.getAsJsonObject().get("artist").getAsJsonObject().get("name").getAsString() + " ");
                }
                artists = sb.toString();
                JsonArray releases = record.getAsJsonObject().get("releases").getAsJsonArray();
                for (JsonElement r : releases) {
                    release = r.getAsJsonObject().get("title").getAsString();
                    break;
                }

            }
        return SongInformation.builder()
                .recordingTitle(title)
                .artistName(artists)
                .albumName(release)
                .build();

    }

}
