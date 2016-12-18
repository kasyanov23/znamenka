package ru.click.reporting.util;

import lombok.val;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class IOUtils {

    public static String fileToString(String path) {
        val resource = new ClassPathResource(path);
        try {
            return StreamUtils.copyToString(resource.getInputStream(), UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e.getMessage(), e);
        }
    }
}
