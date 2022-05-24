package com.MusicBrainProject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongInformation {
    private String albumName;
    private String artistName;
    private String recordingTitle;


}
