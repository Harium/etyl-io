package com.harium.etyl.util.io;

import java.io.*;

public class IOHelper {

    public static final String FILE_PREFIX = "file:///";
    public static final String STREAM_PREFIX = "stream:///";
    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";

    public static final String ENCODING_UTF_8 = "UTF-8";

    public static final String WHITE_SPACE = " ";
    public static final String WINDOWS_SPACING = "%20";

    public static void writeFile(String path, String text) {
        writeFile(path, text, ENCODING_UTF_8);
    }

    public static void writeFile(String path, String text, String charset) {
        Writer writer = null;

        try {

            File file = getFile(path);
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), charset));
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public static File getFile(String path) throws IOException {

        File file = new File(fixPrefixPath(path));

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        return file;
    }

    private static String fixPrefixPath(String path) {

        String filePath = path;

        if (path.startsWith(FILE_PREFIX)) {
            filePath = path.substring(5);
        }

        if (path.contains(WINDOWS_SPACING)) {
            filePath = fixPath(filePath);
        }

        return filePath;
    }

    public static String fixPath(String path) {
        return path.replaceAll(WINDOWS_SPACING, WHITE_SPACE);
    }

    public static boolean startsWithHttp(String path) {
        return path.startsWith(IOHelper.HTTP_PREFIX) || path.startsWith(IOHelper.HTTPS_PREFIX);
    }

    public static void createDirectory(String path) {
        new File(path).mkdirs();
    }

}
