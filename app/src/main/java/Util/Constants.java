package Util;
public class Constants {
    //yt API key
    public static final String ytApiKey="AIzaSyBKjcjhtNJaI4oH99QJq2fsrA8yKQsetBs";
    //OMDb API related URL
    public static final String URL_INFO_LEFT="http://www.omdbapi.com/?i=";
    public static final String URL_LEFT="http://www.omdbapi.com/?s=";
    public static final String URL_RIGHT="&apikey=8ffe700";

    //TMDb api-key
    public static final String APIKEY="b17ab37f395a1dd765fa7ebc6f0a689f";

    //tmdB->imdb : for series only
    public static final String toImdbleft_series="https://api.themoviedb.org/3/tv/";
    //tmdB->imdb : for movie only
    public static final String toImdbleft="https://api.themoviedb.org/3/movie/";
    public static final String toImdbRight="/external_ids?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    //trending_movies_tmdB
    public static final String getTrending="https://api.themoviedb.org/3/trending/movie/day?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    //recent_movies_tmdB
    public static final String getRecentRelease="https://api.themoviedb.org/3/movie/upcoming?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    //popular_movies_tmdB
    public static final String getPopular="https://api.themoviedb.org/3/movie/popular?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US&region=in";

    //top_movies_tmdB
    public static final String getTopRated="https://api.themoviedb.org/3/movie/top_rated?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    //simalar
    public static final String getSimilarDataLeft="https://api.themoviedb.org/3/movie/";
    //in between enter tmdb_movieId...
    public static final String getSimilarDataRight="/similar?api_key=b17ab37f395a1dd765fa7ebc6f0a689f";

    //image_path_URL
    public static final String imagePath="http://image.tmdb.org/t/p//original/";

    public static final String getImageDataLeft="https://api.themoviedb.org/3/find/";
    //in between enter IMDbId...
    public static final String getImageDataRight="?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US&external_source=imdb_id";

    public static final String getVideoDataLeft_series="https://api.themoviedb.org/3/tv/";
    public static final String getVideoDataLeft="https://api.themoviedb.org/3/movie/";
    //in between enter tmdb_movieId...
    public static final String getVideoDataRight="/videos?api_key=b17ab37f395a1dd765fa7ebc6f0a689f&language=en-US";
}
