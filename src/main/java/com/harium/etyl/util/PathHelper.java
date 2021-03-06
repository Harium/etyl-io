package com.harium.etyl.util;

import com.harium.etyl.util.io.IOHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class PathHelper {

    private static final String ASSETS_FOLDER = "assets";

    public static InputStream loadAsset(String path) throws IOException {
        return new FileInputStream(new File(assetsFolder() + path));
    }

    public static String[] listAssets(String path) throws IOException {
        String dir = assetsFolder() + path;
        String[] files = new File(dir).list();
        Arrays.sort(files);
        return files;
    }

    private static String assetsFolder() {
        return currentDirectory() + ASSETS_FOLDER + File.separator;
    }

    public static String currentDirectory() {
        String currentDirectory = "";

        try {
            String path = new File(".").getCanonicalPath().toString();
            currentDirectory = path + File.separator;

            return currentDirectory;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentDirectory;
    }

    public static String currentFileDirectory() {

        String currentDirectory = "";

        try {
            String path = new File(".").getCanonicalPath().toString();
            currentDirectory = IOHelper.FILE_PREFIX + path + File.separator;

            return currentDirectory;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentDirectory;
    }

    public static String upperDirectory(String path) {

        String separator = File.separator;
        if (!path.contains(separator)) {
            separator = "\\";
        }

        return path.substring(0, path.lastIndexOf(separator) + 1);
    }

    public static String filename(String path) {
        int index = path.lastIndexOf(File.separator);
        return path.substring(index);
    }

    public static String currentPath() {
        URL location = PathHelper.class.getProtectionDomain().getCodeSource().getLocation();
        String currentDirectory = IOHelper.FILE_PREFIX + location.getPath();
        return currentDirectory;
    }

    public static String desktopDirectory() {
        String home = System.getProperty("user.home");
        return home + File.separator;
    }

    public static String programFilesDirectory() {
        return System.getenv("ProgramFiles");
    }

    public static String getExternalStoragePath() {
        return currentPath();
    }

    public static String clearPath(String path) {
        String cleanPath = path.replaceAll("/", File.separator);

        final String upper = "../";
        int index = cleanPath.indexOf(upper);
        while (index != -1) {
            int lastFolder = lastFolder(cleanPath, index);
            String before = cleanPath.substring(0, lastFolder - 1);
            String after = cleanPath.substring(index + 3);
            cleanPath = before + after;

            index = cleanPath.indexOf(upper);
        }
        return cleanPath;
    }

    private static int lastFolder(String path, int cursor) {
        int index = cursor;
        while (path.charAt(index) != File.separatorChar) {
            index--;
        }
        return index;
    }

    public static String getExtension(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i >= 0) {
            extension = path.substring(i + 1);
        }
        return extension;
    }

}
