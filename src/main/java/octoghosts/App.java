package octoghosts;

import java.util.List;

import static octoghosts.FilesProcessor.processFiles;

public class App
{
    public static void main(String[] args)
    {
        List<Page> pages = processFiles(args.length > 0 ? args[0] : ".");

        System.out.println("Pages (" + pages.size() + "):");
        for (Page page : pages) {
            System.out.println("\t" + page.getName());
        }
    }
}
