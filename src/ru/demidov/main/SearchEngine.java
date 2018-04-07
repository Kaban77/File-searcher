package ru.demidov.main;

import java.io.*;
import java.util.TreeMap;

public class SearchEngine {

    private TreeMap<String, String> textFiles;

    public SearchEngine() {
        textFiles = new TreeMap<String, String>();
    }

    public void processFolders(File folder) {
        File[] folderContent = folder.listFiles();

        for (File entry : folderContent) {
            if (entry.isDirectory()) {
                processFolders(entry);
                continue;
            }
            processFiles(entry);
        }
    }

    public void createSingleFile(String folder, String fileName) {
        try (PrintWriter writer = new PrintWriter(folder + "\\" + fileName, "UTF-8")) {
            for(String fileContent : textFiles.keySet()) {
                writer.write(textFiles.get(fileContent));
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processFiles(File file) {
        if (file.getName().indexOf(".txt") == -1) {
            return;
        }
        textFiles.put(file.getName(), getFileContent(file));
    }

    private String getFileContent(File file) {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), "UTF-8"))) {

            StringBuilder content = new StringBuilder ();
            String line;
            while((line = in.readLine()) != null) {
                content.append(line + "\n");
            }

            return content.toString();

        } catch(IOException ex) {
            ex.printStackTrace();
            return new String();
        }
    }
}