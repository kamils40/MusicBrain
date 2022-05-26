package com.MusicBrainProject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Result {
    String randomWord;
    String songInformation;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(randomWord).append(" - ");
        sb.append(songInformation.toString());
        return sb.toString();
    }
}
