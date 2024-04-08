package at.ac.fhcampuswien.fhmdb;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at";
    private final OkHttpClient httpClient;
    private final Gson gson;

    public MovieAPI() {
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    public String getAllMovies() throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/movies").newBuilder();
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getMoviesByFilter(String searchText, String genre) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/movies").newBuilder();
        if (searchText != null && !searchText.isEmpty()) {
            urlBuilder.addQueryParameter("search", searchText);
        }
        if (genre != null && !genre.isEmpty()) {
            urlBuilder.addQueryParameter("genre", genre);
        }
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}