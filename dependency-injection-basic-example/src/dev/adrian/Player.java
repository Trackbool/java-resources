package dev.adrian;

import dev.adrian.domain.Playable;

public class Player {
    private String name;
    private Playable playable;

    public Player(String name, Playable playable) {
        this.name = name;
        this.playable = playable;
    }

    public void play() {
        System.out.println("My name is " + name + " and I´m going to play");
        playable.play();
        System.out.println("Okay, it´s time to stop");
    }
}