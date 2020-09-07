package Model;
public class MovieData{
 String Title,Type,Year,Poster,imdbId;
 String searchQuery;
public MovieData(){

        }

public String getTitle() {
        return Title;
        }

public void setTitle(String title) {
        Title = title;
        }

public String getType() {
        return Type;
        }

public void setType(String type) {
        Type = type;
        }

public String getYear() {
        return Year;
        }

public void setYear(String year) {
        Year = year;
        }

public String getPoster() {
        return Poster;
        }

public void setPoster(String poster) {
        Poster = poster;
        }

public String getImdbId() {
        return imdbId;
        }

public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
        }

        public String getSearchQuery() {
                return searchQuery;
        }

        public void setSearchQuery(String searchQuery) {
                this.searchQuery = searchQuery;
        }
}
