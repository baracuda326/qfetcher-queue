package com.qfetcher.qfetcher.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry Asmalouski
 * @version 2.0
 * class contain constants for all services and repositories
 */
public final class Constants {
    public static final String IMAGE_TYPE = "image";
    public static final String CSV_TYPE = "csv";
    public static final String JSON_TYPE = "json";
    public static final String KEY_TEXT = "text";
    public static final String FIELD_DESCRIPTION = "description";
    public static final List<String> IMAGE_FORMAT_LIST = Arrays.asList("png", "gif", "jpg", "tiff", "bmp");
    public static final List<String> FORMAT_LIST = Arrays.asList(IMAGE_TYPE, CSV_TYPE, JSON_TYPE);

    private Constants() {
    }
}
