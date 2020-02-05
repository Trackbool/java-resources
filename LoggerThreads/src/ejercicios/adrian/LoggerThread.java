package ejercicios.adrian;

public class LoggerThread extends Thread {
    private Log log;
    private String text;

    public LoggerThread(Log log, String text) {
        this.log = log;
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            log.write(text);
        }
    }
}
