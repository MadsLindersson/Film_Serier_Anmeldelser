package org.example.film_serier_anmeldelser.Controller;

import org.example.film_serier_anmeldelser.Model.Movie;
import org.example.film_serier_anmeldelser.Service.MovieRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        List<Movie> movieRepo = MovieRepo.getAllMovies();

        model.addAttribute("Movies", movieRepo);

        return "Home";
    }

    @GetMapping("/CreateMoviePage")
    public String createMovie (Model model)    {
        return "CreateMoviePage";
    }

    @PostMapping("/CreateMoviePage")
    public String addMovieToDB(@ModelAttribute Movie movie)    {

        MovieRepo.addMovieToDatabase(movie);

        return "redirect:/";
    }

    @PostMapping("/deleteMovie")
    public String deleteMovie (@RequestParam("movieId") int movieId, Model model)    {

        MovieRepo.deleteMovieFromDatabase(movieId);

        return "redirect:/";
    }
//todo Virker ikke - Ser ud til at der returneres en null movie - men metoden får fat i en movie i DB...
    @GetMapping("/showReview")
    public String showReview(@RequestParam("movieId") int movieId, Model model)    {

        Movie movie = MovieRepo.getMovieFromDatabase(movieId);

        if (movie == null) {
            return "redirect:/";
        }

        model.addAttribute("movie", movie);

        return "ShowReview";
    }

    @GetMapping("/editWithoutReview")
    public String editWithoutReview (int movieId, Model model)    {
        Movie movie = MovieRepo.getMovieFromDatabase(movieId);
        if (movie == null) {
            return "redirect:/";
        }
        model.addAttribute("movie", movie);
        return "editWithoutReview";
    }
    //todo for at redigere i en film skal der være fat i ID på filmen - den har ikke fat lige nu
    @PostMapping("/editWithoutReview")
    public String editWithoutReviewPost (@ModelAttribute Movie movie, int movieId)    {

        MovieRepo.editWithoutReview(movie, movieId);

        return "redirect:/";
    }
}
