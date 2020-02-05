import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> words = null;
        try {
            words = readWordsFile();
        } catch (IOException e1) {
            throw new Exception(e1);
        }
        
        try(ServerSocket server = new ServerSocket(9090)){
            System.out.println("Listening...");
            while (true) {
                Socket client = server.accept();
                
                String randomWord = getRandomWord(words);
                SecretWord secretRandomWord = new SecretWord(randomWord);
                System.out.println("Client connected. Word: " + randomWord);
                
                Game game = new Game(client, secretRandomWord);
                new Thread(game).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readWordsFile() throws IOException {
        List<String> words = new ArrayList<>();
        
        String wordsFilePath = System.getProperty("user.dir") + "/files/words.dat";
        try(BufferedReader fileReader = new BufferedReader(new FileReader(wordsFilePath))){
            String line = fileReader.readLine();
            
            while (line != null) {
                words.add(line);
                line = fileReader.readLine();
            }
        } catch(IOException e) {
            throw e;
        }
        
        return words;
    }
    
    private static String getRandomWord(List<String> words) {
        int randomIndex = new Random().nextInt(words.size());   
        return words.get(randomIndex);
    }
}
