package com.nix.files.util;


import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FilesUtil {
    public static List<String> getLinesWithSubstring(String path, String substring) {
        StringBuilder result = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null) {
                if (line.contains(substring))
                    result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(result.toString().split("\n"));
    }

    public static void copyCatalog(String root, String pathTo) throws IOException {
        File file = new File(root);
        copyCatalog(file, pathTo + "\\" + file.getName());
    }

    private static void copyCatalog(File currentFile, String pathTo) throws IOException {
        String path;
        File copyDir = new File(pathTo);
        File copyFile;

        if (!copyDir.mkdir())
            throw new IOException("Директория c путём " + copyDir.getAbsolutePath() + " не была создана");

        if (currentFile.isDirectory()) {
            File[] filesList = currentFile.listFiles();

            if (filesList == null)
                return;

            for (File file : filesList) {
                path = pathTo + "\\" + file.getName();
                if (file.isFile()) {
                    copyFile = new File(path);
                    try {
                        if (!copyFile.createNewFile())
                            throw new IOException("Файл " + copyFile.getAbsolutePath() + " не был создан");

                        if (file.length() != 0)
                            copyFromFile(file, copyFile);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    copyCatalog(file, path);
            }
        }
    }

    private static void copyFromFile(File from, File to) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(from)); PrintStream out = new PrintStream(new FileOutputStream(to))) {
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
