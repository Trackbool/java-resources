import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try (Socket server = new Socket("127.0.0.1", 9090)) {
            PrintWriter socketOut = new PrintWriter(server.getOutputStream(), true);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(server.getInputStream()));
            
            String message;
            do {
                message = socketIn.readLine();
                System.out.println(message);
                if(!isGameFinalized(message)) {
                    Character letter = sc.nextLine().charAt(0);
                    socketOut.println(letter);
                }
            } while(!isGameFinalized(message));
        }  catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        sc.close();
    }

    private static boolean isGameFinalized(String message) {
        return message.contains("ganado") ||message.contains("perdido");
    }
}
