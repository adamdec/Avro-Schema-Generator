package com.nokia.util.avro.schemagen;

import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

public class FileUtils {

    public static String makeRelativePath(File file) {
        File cur = new File("");
        String curPath = cur.getAbsolutePath();
        String filePath = file.getAbsolutePath();

        if (filePath.startsWith(curPath)) {
            return filePath.substring(curPath.length() + 1);
        } else {
            return filePath;
        }
    }

    public static void deleteFile(File f) throws MojoFailureException {
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                deleteFile(file);
            }
        }

        boolean result = f.delete();
        if (!result) {
            throw new MojoFailureException("Could not remove file '" + f.getAbsolutePath() + "'.");
        }
    }
}
