import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLScraper {
    public static void main(String[] args) throws IOException {
        // Set the homepage URL to scrape
        String homepage = "https://www.nytimes.com/";

        // Initialize a HashSet to store unique URLs
        HashSet<String> uniqueUrls = new HashSet<>();

        // Initialize a Pattern to match article URLs
        Pattern articlePattern = Pattern.compile("^https://www\\.nytimes\\.com/\\d{4}/\\d{2}/\\d{2}/[a-z\\-]+\\.html$");

        // Initialize a Random object to select random URLs
        Random random = new Random();

        // Scrape the homepage content
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(homepage).openStream()));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        // Find all URLs in the homepage content
        ArrayList<String> urls = new ArrayList<>();
        Matcher matcher = Pattern.compile("href=\"(.*?)\"").matcher(content.toString());
        while (matcher.find()) {
            String url = matcher.group(1);
            if (!url.startsWith("http")) {
                url = homepage + url;
            }
            urls.add(url);
        }

        // Loop through random URLs and check if they match the article pattern
        int count = 0;
        while (uniqueUrls.size() < 1000 && count < 10000) {
            int randomIndex = random.nextInt(urls.size());
            String randomUrl = urls.get(randomIndex);
            if (articlePattern.matcher(randomUrl).matches() && !uniqueUrls.contains(randomUrl)) {
                uniqueUrls.add(randomUrl);
                count++;
                System.out.println(randomUrl);
            }
        }
    }
}
