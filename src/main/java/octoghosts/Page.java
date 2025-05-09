package octoghosts;

import java.nio.file.Path;

public class Page {
    private final String name;
    private Path markdownFilePath;

    public Page(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public Path getMarkdownFilePath() { return markdownFilePath; }

    public void setMarkdownFilePath(Path path) { markdownFilePath = path; }
}
