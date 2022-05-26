package com.MusicBrainProject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongInformation {
    private String albumName;
    private String artistName;
    private String recordingTitle;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Artist: ").append(artistName).append(" ");
        sb.append("Album: ").append(albumName).append(" ");
        sb.append("Track: ").append(recordingTitle);
        return sb.toString();
    }
}
