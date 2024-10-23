package org.example.film_serier_anmeldelser.Model;

import java.util.Comparator;

public class MovieComparator {

        public static Comparator<Movie> getComparator (int sort)  {

            switch (sort) {
                case 1 -> {
                    return Comparator.comparing(Movie::getTitle);
                }
                case 2 -> {
                    return Comparator.comparing(Movie::getTitle).reversed();
                }
                case 3 -> {
                    return Comparator.comparing(Movie::getDirector);
                }
                case 4 -> {
                    return Comparator.comparing(Movie::getDirector).reversed();
                }
                case 5 -> {
                    return Comparator.comparing(Movie::getGenre);
                }
                case 6 -> {
                    return Comparator.comparing(Movie::getGenre).reversed();
                }
                case 7 -> {
                    return Comparator.comparing(Movie::getMyRating);
                }
                case 8 -> {
                    return Comparator.comparing(Movie::getMyRating).reversed();
                }
                case 9 -> {
                    return Comparator.comparing(Movie::getYear);
                }
                case 10 -> {
                    return Comparator.comparing(Movie::getYear).reversed();
                }
                default -> {
                    return Comparator.comparing(Movie::getTitle);
                }
            }
    }
}

