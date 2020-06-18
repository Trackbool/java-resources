package dev.adrian.domain;

public class Doll implements Playable {
    @Override
    public void play() {
        System.out.println("Playing with the doll...");
    }
}
