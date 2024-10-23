package org.example.film_serier_anmeldelser.Service;

import org.example.film_serier_anmeldelser.Model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class MovieRepo {


    public static List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();

        try (Connection con = getConnection()){

            if (con == null) {
                System.out.println("Failed to establish database connection.");
                return movieList;
            }

            PreparedStatement ps = con.prepareStatement("SELECT * FROM movie");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getString("director"),
                        rs.getString("genre"),
                        rs.getInt("myRating"),
                        rs.getString("myReview"));

                movie.setID(rs.getInt("ID")); //Er den nødvendig?
                movieList.add(movie);

            }

        } catch (SQLException e) {
            System.out.println("Something went wrong - " + e.getMessage());
            throw new RuntimeException(e);
        }

        return movieList;
    }

    public static void addMovieToDatabase(Movie movie) {

        try (Connection con = getConnection())  {
            if (con == null) {
                System.out.println("Failed to establish database connection.");
            }

            PreparedStatement ps = con.prepareStatement("INSERT INTO movie (title, year, director, genre, myRating, myReview) VALUES (?, ?, ?, ?, ?, ?)");

            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getYear());
            ps.setString(3, movie.getDirector());
            ps.setString(4, movie.getGenre());
            ps.setInt(5, movie.getMyRating());
            ps.setString(6, movie.getMyReview());

            ps.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Something went wrong - " + e.getMessage());
        }
    }

    public static void deleteMovieFromDatabase(int movieID) {
        try (Connection con = getConnection()) {
            if (con == null) {
                System.out.println("Failed to establish database connection.");
            }

            PreparedStatement ps = con.prepareStatement("DELETE FROM movie WHERE ID = ?");
            ps.setInt(1, movieID);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Movie getMovieFromDatabase(int movieID) {
        try (Connection con = getConnection())  {
            if (con == null) {
                System.out.println("Failed to establish database connection.");
            }

            PreparedStatement ps = con.prepareStatement("SELECT * FROM movie WHERE ID = ?");

            ps.setInt(1, movieID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Movie movie = new Movie(
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getString("director"),
                        rs.getString("genre"),
                        rs.getInt("myRating"),
                        rs.getString("myReview")
                );

                movie.setID(rs.getInt("ID"));

//                System.out.println(movie.getTitle());
//                System.out.println(movie.getYear());
//                System.out.println(movie.getDirector());
//                System.out.println(movie.getGenre());
//                System.out.println(movie.getMyRating());
//                System.out.println(movie.getMyReview());

                return movie;

            } else {
                System.out.println("Movie not found");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Something went wrong - " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void editWithoutReview (Movie movie, int movieId) {

            try (Connection con = getConnection())  {
                if (con == null) {
                    System.out.println("Failed to establish database connection.");
                }

                PreparedStatement ps = con.prepareStatement("UPDATE movie SET " +
                        "title = ?, " +
                        "year = ?, " +
                        "director = ?, " +
                        "genre = ?, " +
                        "myRating = ? " +
                        "WHERE ID = ?");

                ps.setString(1, movie.getTitle());
                ps.setInt(2, movie.getYear());
                ps.setString(3, movie.getDirector());
                ps.setString(4, movie.getGenre());
                ps.setInt(5, movie.getMyRating());
                ps.setInt(6, movieId);

                ps.executeUpdate();

            }catch (SQLException e) {
                System.out.println("Something went wrong - " + e.getMessage());
            }
    }

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(Secret.connectionString, Secret.usernameString, Secret.passwordString);
        } catch (Exception e) {
            System.out.println("Der opstod en fejl ved oprettelsen til databasen: " + e.getMessage());
            return null;
        }
    }

}

