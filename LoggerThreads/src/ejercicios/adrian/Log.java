package ejercicios.adrian;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public void write(String text) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("./logfile.txt", true)))) {
            String currentThread = Thread.currentThread().getName();
            String currentDate = getCurrentDate();
            writer.print(currentThread + " - " + currentDate + " |");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.println(" " + currentThread + " - " + currentDate + " | " + text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss.SSS");
        return format.format(new Date());
    }
}
