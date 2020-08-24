package com.rlsp.springboot.fundamentals.util;

import com.rlsp.springboot.fundamentals.domain.Anime;

public class AnimeCreator {


    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
            .name("createAnimeToBeSaved")
            .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
            .name("createValidAnime")
            .id(1)
            .build();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
            .name("createValidUpdatedAnime")
            .id(1)
            .build();
    }
    
}
