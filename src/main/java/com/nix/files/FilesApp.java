package com.nix.files;

import com.nix.files.util.FilesUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilesApp {
    private final static String path = "file.txt";
    private final static String substring = "veg";
    private final static String pathFrom = "catalog Recipes";
    private final static String pathTo = "catalog Copy";

    public static void main(String[] args) {
        List<String> strings = FilesUtil.getLinesWithSubstring(path, substring);
        System.out.println("1. Строки из файла " + path + ", которые содержат подстроку \"" + substring + "\"");
        strings.forEach(System.out::println);
        System.out.println();

        File dir = new File(pathTo);
        if (dir.mkdir() || dir.exists()) {
            try {
                FilesUtil.copyCatalog(pathFrom, pathTo);
                System.out.println("2. Папка \"" + pathFrom + "\" скопирована в папку \"" + pathTo + "\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
