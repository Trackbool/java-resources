package dev.adrian.domain;

public class PlayStation implements Playable {
    @Override
    public void play() {
        System.out.println("Turning on the PS4...");
        System.out.println("Playing...");
        System.out.println("Shuting down the PS4...");
    }
}
