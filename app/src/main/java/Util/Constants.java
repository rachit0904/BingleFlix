package Util;
public class Constants {
    //OMDb API related URL
    public static final String URL_INFO_LEFT="http://www.omdbapi.com/?i=";
    public static final String URL_LEFT="http://www.omdbapi.com/?s=";
    public static final String URL_RIGHT="&apikey=8ffe700";
    //TMDb API related URL
    public static final String APIKEY="b17ab37f395a1dd765fa7ebc6f0a689f";
    public static final String getTrending="https://api.themoviedb.org/3/trending/movie/day?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    public static final String getImageDataLeft="https://api.themoviedb.org/3/find/";
    //in between enter IMDbId...
    public static final String getImageDataRight="?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US&external_source=imdb_id";

    public static final String getGenere="https://api.themoviedb.org/3/genre/movie/list?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US";

    public static final String searchMoviesLeft="https://api.themoviedb.org/3/search/multi?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US&query=";
    //in between the query goes...
    public static final String searchMoviesRight="&page=1&include_adult=false";

    public static final String imagePath="https://image.tmdb.org/t/p/original";
}
