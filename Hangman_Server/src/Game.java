import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game implements Runnable {
    private final Socket client;
    private final SecretWord secretWord;
    private static final int MAX_ATTEMPS = 5;
    
    public Game(Socket client, SecretWord secretWord) {
        this.client = client;
        this.secretWord = secretWord;
    }

    @Override
    public void run() {
        try (PrintWriter socketOut = new PrintWriter(client.getOutputStream(), true);
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

            int attemps = 0;
            socketOut.println(secretWord.get());
            while (!this.secretWord.isGuessed() && attemps < MAX_ATTEMPS) {
                Character letter = socketIn.readLine().charAt(0);
                boolean letterGuessed = secretWord.guessLetter(letter);
                if(letterGuessed) {
                    if(!this.secretWord.isGuessed()) {
                        socketOut.println(secretWord.get());
                    } else {
                        socketOut.println("Has ganado");
                    }
                } else {
                    attemps++;
                    if(attemps < MAX_ATTEMPS) {
                        socketOut.println("Has fallado. Llevas " + attemps + " intento/s");
                    } else {
                        socketOut.println("Has perdido");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            try {
                client.close();
            } catch (IOException ignored) {}
        }
    }
    
}
