package com.qfetcher.qfetcher.utils;

import com.qfetcher.qfetcher.exception.DataResourceException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FilesClearUtils {
    public static void removeDirFile(String fileName){
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            throw new DataResourceException("Delete file with name : " + fileName + " impossible!");
        }
    }
}
