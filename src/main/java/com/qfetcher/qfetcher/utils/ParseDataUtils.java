package com.qfetcher.qfetcher.utils;

import com.qfetcher.qfetcher.exception.ConnectionTimeOutException;
import com.qfetcher.qfetcher.exception.DataResourceException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 */
public final class ParseDataUtils {
    private static final int CONNECT_TIMEOUT = 30_000;
    private static final int READ_TIMEOUT = 30_000;

    private ParseDataUtils() {
    }

    //****************************************************************************

    /**
     * @param urlResource contains URL resource
     * @param fileName    create file by current name (example.format)
     * @throws ConnectionTimeOutException
     */
    public static void createFileFromUrl(String urlResource, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(urlResource), new File(fileName), CONNECT_TIMEOUT, READ_TIMEOUT);
        } catch (IOException e) {
            throw new ConnectionTimeOutException("Request Timeout");
        }
    }

    //****************************************************************************

    /**
     * @param urlResource
     * @return current name by url (example.format)
     */
    public static String getFileName(String urlResource) {
        int index = urlResource.lastIndexOf("/");
        return urlResource.substring(index + 1);
    }

    //****************************************************************************

    /**
     * @param urlResource example "https://some.url/manifest.dat" or "https://some.url/data.json"
     * @return format file example(dat, json, csv, image("png", "gif", "jpg", "tiff", "bmp")
     */
    public static String getFormatFile(String urlResource) {
        String fileName = getFileName(urlResource);
        String[] lineArray = fileName.split("\\.");
        return lineArray[1];
    }

    //****************************************************************************

    /**
     * Encode image to String using Base64 encoder
     *
     * @param imageName current name (example.format)
     * @return encoded image as string
     * @throws DataResourceException
     */
    public static String baseImageEncoder(String imageName) {
        try {
            return Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(imageName)));
        } catch (IOException e) {
            throw new DataResourceException("No valid data resource!");
        }
    }
//****************************************************************************
}