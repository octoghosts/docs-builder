package octoghosts;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesProcessor {
    private FilesProcessor() {}

    public static List<Page> processFiles(String path) {
        List<Path> allFiles = getFilesInDirectory(path);
        List<Path> markdownFiles = getMarkdownFiles(allFiles);
        return generatePages(markdownFiles);
    }

    private static List<Path> getFilesInDirectory(String path) {
        Path directoryPath = Paths.get(path);
        List<Path> files = new ArrayList<>();

        if (Files.isDirectory(directoryPath)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath);) {
                directoryStream.forEach(file -> {
                    files.add(file.toAbsolutePath());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Require path to a directory, got: " + path);
        }

        return files;
    }

    private static List<Path> getMarkdownFiles(List<Path> filePaths) {
        List<Path> markdownFiles = new ArrayList<>();

        for (Path file : filePaths) {
            if (file.toString().endsWith(".md") || file.toString().endsWith(".markdown")) {
                markdownFiles.add(file);
            }
        }

        return markdownFiles;
    }

    private static List<Page> generatePages(List<Path> markdownFiles) {
        List<Page> pages = new ArrayList<>();

        for (Path file : markdownFiles) {
            Page page = new Page(file.getFileName().toString());
            page.setMarkdownFilePath(file);
            pages.add(page);
        }

        return pages;
    }
}
