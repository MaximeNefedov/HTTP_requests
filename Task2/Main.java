package Task2;

import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String CURRENT_URL = "https://api.nasa.gov/planetary/apod?api_key=aWTqRSI2Eds1cIeZ5hceD8ta7STzuGCclHduejyC";

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(CURRENT_URL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Content content = gson.fromJson(body, Content.class);
            String URL = content.getUrl();
            HttpGet imageRequest = new HttpGet(URL);
            CloseableHttpResponse imageResponse = httpClient.execute(imageRequest);
            byte[] bytes = imageResponse.getEntity().getContent().readAllBytes();
            File file = new File("image.jpg");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
            bos.flush();
            bos.close();
            imageResponse.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
