package ejercicios.adrian;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher extends Thread {
    private String fileName;
    private String wordToSearch;
    private ProcessStatus status;
    private List<Integer> lines;

    public FileSearcher(String fileName, String wordToSearch) {
        this.fileName = fileName;
        this.wordToSearch = wordToSearch;
        this.status = ProcessStatus.INACTIVE;
        this.lines = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public String getWord() {
        return wordToSearch;
    }

    public List<Integer> getLinesFound() {
        return lines;
    }

    public int getTotalLinesFound() {
        return lines.size();
    }

    public ProcessStatus getStatus() {
        return status;
    }

    @Override
    public void run() {
        status = ProcessStatus.SEARCHING;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            int lineNumber = 1;
            while (line != null) {
                if(this.isInterrupted()){ return; }

                saveLineNumberIfContainsWord(line, lineNumber);
                line = reader.readLine();
                lineNumber++;

                try{
                    Thread.sleep(600); //Set delay
                } catch (InterruptedException e){ return; }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            status = ProcessStatus.FINISHED;
        }
    }

    private void saveLineNumberIfContainsWord(String line, int lineNumber) {
        if (line.contains(wordToSearch)) {
            lines.add(lineNumber);
        }
    }

    @Override
    public String toString() {
        return fileName + " " + wordToSearch + " " + getTotalLinesFound() + " " + status;
    }

    public enum ProcessStatus {
        INACTIVE, SEARCHING, FINISHED
    }
}
