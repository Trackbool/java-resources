package dev.adrian;

import dev.adrian.domain.Doll;
import dev.adrian.domain.PlayStation;
import dev.adrian.domain.Playable;

public class Main {

    public static void main(String[] args) {
	    Playable playable1 = new Doll();
	    Playable playable2 = new PlayStation();

	    Player player1 = new Player("Adrian", playable1);
	    player1.play();

		System.out.println("---------------");

		Player player2 = new Player("Marta", playable2);
		player2.play();
    }
}
