package com.example.adylanrff.kultivafarmer;
import com.google.gson.annotations.SerializedName;

public class Crop {
    private String name;
    private int price;
    private String unit;
    private int status;

    public Crop(String name, int price, String unit, int status) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //    @SerializedName("poster_path")
//    private String posterPath;
//    @SerializedName("adult")
//    private boolean adult;
//    @SerializedName("overview")
//    private String overview;
//    @SerializedName("release_date")
//    private String releaseDate;
//    @SerializedName("genre_ids")
//    private List<Integer> genreIds = new ArrayList<Integer>();
//    @SerializedName("id")
//    private Integer id;
//    @SerializedName("original_title")
//    private String originalTitle;
//    @SerializedName("original_language")
//    private String originalLanguage;
//    @SerializedName("title")
//    private String title;
//    @SerializedName("backdrop_path")
//    private String backdropPath;
//    @SerializedName("popularity")
//    private Double popularity;
//    @SerializedName("vote_count")
//    private Integer voteCount;
//    @SerializedName("video")
//    private Boolean video;
//    @SerializedName("vote_average")
//    private Double voteAverage;

}
