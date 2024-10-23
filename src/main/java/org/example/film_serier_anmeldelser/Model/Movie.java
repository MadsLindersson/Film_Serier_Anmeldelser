package org.example.film_serier_anmeldelser.Model;

public class Movie  {

    private int ID;
    private String title;
    private int year;
    private String director;
    private String genre;
    private int myRating;
    private String myReview;

    public Movie(String title, int year, String director, String genre, int myRating, String myReview) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.setMyRating(myRating);
        this.myReview = myReview;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getMyRating() {
        return myRating;
    }

    public void setMyRating(int myRating) {
        this.myRating = myRating;
    }

    public String getMyReview() {
        return myReview;
    }

    public void setMyReview(String myReview) {
        this.myReview = myReview;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
