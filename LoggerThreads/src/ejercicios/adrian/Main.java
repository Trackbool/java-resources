package ejercicios.adrian;

public class Main {

    public static void main(String[] args) {
        Log log = new Log();

        for (int i = 0; i < 5; i++) {
            LoggerThread t = new LoggerThread(log, "logged text");
            t.setName("Logger " + i);
            t.start();
        }
    }
}
