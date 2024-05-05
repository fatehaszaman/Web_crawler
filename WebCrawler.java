import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> urlQueue;
    private Set<String> visitedURLs;
    private Set<String> excludedWords;

    public WebCrawler() {
        urlQueue = new LinkedList<>();
        visitedURLs = new HashSet<>();
        excludedWords = new HashSet<>();
        excludedWords.add("politics");
        excludedWords.add("covid");
        excludedWords.add("interactive");
        excludedWords.add("coronavirus");
        excludedWords.add("ukraine");
        excludedWords.add("biden");
        excludedWords.add("result");
        excludedWords.add("by");
        excludedWords.add("trump");
        excludedWords.add("korea");
        excludedWords.add("section");
        excludedWords.add("polls");
        excludedWords.add("china");
        excludedWords.add("iran");
        excludedWords.add("middleeast");
        excludedWords.add("crossword");
        excludedWords.add("europe");
        excludedWords.add("climate");
        excludedWords.add("abortion");
        excludedWords.add("inflation");
        excludedWords.add("russia");
        excludedWords.add("economy");
        excludedWords.add("business");
        excludedWords.add("nyregion");
        excludedWords.add("/by/");
        excludedWords.add("ezra");
        excludedWords.add("tag");
        excludedWords.add("athlete");
        excludedWords.add("football");
        excludedWords.add("music");
        excludedWords.add("art");
        excludedWords.add("game");
        excludedWords.add("protest");
        excludedWords.add("court");
        excludedWords.add("technology");
        excludedWords.add("soccer");
        excludedWords.add("/recommendations/");
        excludedWords.add("/titles/");
        excludedWords.add("/authors/");
        excludedWords.add("/help/");
        excludedWords.add("/topic/person");
        excludedWords.add("/spotlight/");
        excludedWords.add("/series/");
        excludedWords.add("/puzzles/");
        excludedWords.add("/service/");
        excludedWords.add("aponline");
        excludedWords.add("events/speakers");
        excludedWords.add("/topic/");





    }

    public void crawl(String rootURL, int maxUrls, boolean excludeWirecutterLinks) {
        urlQueue.add(rootURL);
        visitedURLs.add(rootURL);

        while (!urlQueue.isEmpty() && visitedURLs.size() < maxUrls) {
            // remove the next url string from the queue to begin traverse.
            String url = urlQueue.remove();
            String rawHTML = "";
            try {
                // create url with the string.
                URL u = new URL(url);
                BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                String inputLine = in.readLine();

                // read every line of the HTML content in the URL
                // and concat each line to the rawHTML string until every line is read.
                while (inputLine != null) {
                    rawHTML += inputLine;

                    inputLine = in.readLine();
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // match any URLs in the HTML and add them to the queue if not already visited
            String urlPattern = "<a[^>]+href\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
            Pattern pattern = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(rawHTML);

            while (matcher.find() && visitedURLs.size() < maxUrls) {
                String actualURL = matcher.group(1);

                boolean excludeURL = false;
                for (String word : excludedWords) {
                    if (actualURL.contains(word)) {
                        excludeURL = true;
                        break;
                    }
                }

                if (!excludeURL && !visitedURLs.contains(actualURL) && actualURL.startsWith("https://www.nytimes.com/") &&
                        (!excludeWirecutterLinks || !actualURL.contains("wirecutter"))) {
                    visitedURLs.add(actualURL);
                    System.out.println(actualURL);
                    urlQueue.add(actualURL);
                }
            }
        }
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        String rootURL = "https://www.nytimes.com/2022/11/29/business/architecture-mock-up-waste-reuse.html";
        int maxUrls = 50000;
        boolean excludeWirecutterLinks = true;
        crawler.crawl(rootURL, maxUrls, excludeWirecutterLinks);
    }

}
