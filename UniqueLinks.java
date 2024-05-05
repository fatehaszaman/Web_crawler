import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class UniqueLinks {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Links.txt"));
        HashSet<String> uniqueLinks = new HashSet<>();

        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith("https://www.nytimes.com") && line.endsWith(".html")) {
                uniqueLinks.add(line);
            }
            line = reader.readLine();
        }

        for (String link : uniqueLinks) {
            System.out.println(link);
        }

        reader.close();
    }
}
