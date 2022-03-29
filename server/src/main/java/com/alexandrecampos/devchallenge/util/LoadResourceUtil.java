package com.alexandrecampos.devchallenge.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class LoadResourceUtil {

    private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

    private static final ResourceLoader RESOURCE_LOADER = new DefaultResourceLoader();

    private LoadResourceUtil() {
    }

    public static String getResource(String resourcePath) {
        if (!CACHE.containsKey(resourcePath)) {
            synchronized (LoadResourceUtil.class) {
                if (!CACHE.containsKey(resourcePath)) {
                    try (InputStream in = RESOURCE_LOADER.getResource(resourcePath).getInputStream();
                         Reader reader = new InputStreamReader(in, UTF_8)) {
                        CACHE.put(resourcePath, FileCopyUtils.copyToString(reader));
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }
            }
        }
        return CACHE.get(resourcePath);
    }
}