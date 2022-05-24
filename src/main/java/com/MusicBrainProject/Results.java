package com.MusicBrainProject;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Results {
    String randomWord;
    String songInformation;
}
