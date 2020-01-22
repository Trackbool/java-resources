package ejercicios.adrian;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void showMenu() {
        System.out.println("1. Buscar palabra");
        System.out.println("2. Mostrar estado de las búsquedas");
        System.out.println("3. Salir");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        List<FileSearcher> fileSearchers = new ArrayList<>();
        do {
            showMenu();
            System.out.println("Elija una opción: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Introduce el nombre del fichero: ");
                    String fileName = sc.nextLine();
                    System.out.println("Introduce la palabra a buscar: ");
                    String wordToSearch = sc.nextLine();

                    FileSearcher fileSearcher = new FileSearcher(fileName, wordToSearch);
                    fileSearchers.add(fileSearcher);
                    fileSearcher.start();
                    break;
                case "2":
                    if (fileSearchers.size() > 0) {
                        for (int i = 0; i < fileSearchers.size(); i++) {
                            FileSearcher s = fileSearchers.get(i);
                            System.out.println((i + 1) + " " + s.toString());
                        }
                        int searchNumber;
                        do {
                            System.out.println("Elija una búsqueda o 0 para volver al menú principal: ");
                            searchNumber = Integer.parseInt(sc.nextLine());
                        } while (searchNumber < 0 || searchNumber > fileSearchers.size());

                        if (searchNumber > 0) {
                            int searchIndex = searchNumber - 1;
                            FileSearcher selectedFileSearcher = fileSearchers.get(searchIndex);
                            if (selectedFileSearcher.getTotalLinesFound() > 0) {
                                System.out.println("Se encontró la palabra " + selectedFileSearcher.getWord() + " en las líneas: "
                                        + selectedFileSearcher.getLinesFound().toString());
                            } else {
                                System.out.println("No se ha encontrado la palabra");
                            }
                        }
                    } else {
                        System.out.println("No hay búsquedas");
                    }
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    for (FileSearcher s : fileSearchers) {
                        if (s.isAlive()) {
                            s.interrupt();
                        }
                    }
                    exit = true;
                    break;
            }
        } while (!exit);
        sc.close();
    }
}
