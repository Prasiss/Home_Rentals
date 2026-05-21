package com.HomeRental.utils;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    /**
     * Extracts file extension from filename.
     * @param fileName Name of the file
     * @return File extension including dot (e.g., .jpg)
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * Checks whether uploaded file is an image.
     * @param part Uploaded file part
     * @return true if file is image, false otherwise
     */
    public static boolean isImage(Part part) {
        String contentType = part.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    /**
     * Builds a file name using identifier and extension.
     * @param identifier Unique identifier (e.g., userId or timestamp)
     * @param extension File extension
     * @return Combined file name
     */
    public static String buildFileName(String identifier, String extension) {
        return identifier + extension;
    }

    /**
     * Saves uploaded file to server directory.
     * @param part Uploaded file part
     * @param uploadDir Directory path to save file
     * @param fileName Name of file to save as
     * @throws IOException if file saving fails
     */
    public static void saveFile(Part part, String uploadDir, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);

        try (InputStream inputStream = part.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}