import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintLinksInAlphabeticalOrder {
    public static void main(String[] args) {
        try {
            String filePath = "/Users/fateha/Documents/SortedNyTimes.txt/"; // change <your_username> to your actual Mac username
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            ArrayList<String> links = new ArrayList<>();
            Pattern pattern = Pattern.compile("<a\\s+[^>]*href\\s*=\\s*\"([^\"]+)\"[^>]*>(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    links.add(matcher.group(1));
                }
            }
            reader.close();
            Collections.sort(links);
            System.out.println("Number of links found: " + links.size());
            for (String link : links) {
                System.out.println(link);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
