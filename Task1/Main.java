package Task1;

import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static final String REMOTE_SERVICE_URL = "https://cat-fact.herokuapp.com/facts";

    public static void main(String[] args) throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URL);
        CloseableHttpResponse response = httpClient.execute(request);
        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        JSONParser jsonParser = new JSONParser();
        List<Post> allPosts = new ArrayList<>();
        Gson gson = new Gson();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(body);
        Object all = jsonObject.get("all");
        JSONArray jsonArray = (JSONArray) all;

        for (int i = 0; i < jsonArray.size(); i++) {
            Post post = gson.fromJson(jsonArray.get(i).toString(), Post.class);
            allPosts.add(post);
        }

        List<Post> sortedPosts = allPosts.stream().filter(value -> value.getUpvotes() > 0).collect(Collectors.toList());
        System.out.println(sortedPosts);
        httpClient.close();
        response.close();

    }
}
