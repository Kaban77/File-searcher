package ru.demidov.main;

import java.io.File;

public class Start {

        public static void main(String[] args) {
            SearchEngine searchEngine = new SearchEngine();
            File folder = new File("C:\\test");

            searchEngine.processFolders(folder);
            searchEngine.createSingleFile("C:\\test", "result.txt");
        }
}
