import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class NYTimesURLGenerator {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        String base_url = "hhttps://www.nytimes.com/2023/04/10/health/abortion-ruling-pharma-executives.html";

        for (int i = 0; i < 200; i++) {
            String url = base_url + rand.nextInt(1000000);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                System.out.println(url);
            }
        }
    }
}
