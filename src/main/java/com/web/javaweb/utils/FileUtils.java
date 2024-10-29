package com.web.javaweb.utils;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

/**
 * @author jyzxc
 * @since 2024-10-29
 */
public class FileUtils {
    // 生成文件的SHA256值
    public static String calculateSHA256(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        byte[] hashBytes = digest.digest(fileBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}