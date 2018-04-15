package daw.controller;

import daw.models.Movie;
import daw.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {

    private final MovieService movieService;

    @Autowired
    public WebController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/login")
    public ModelAndView login() {

        // Nos construimos el modelo a devolver y su template sin html
        ModelAndView modelAndView = new ModelAndView("login_template");

        return modelAndView;

    }


    @RequestMapping("/home")
    public ModelAndView home() {

        List<Movie> movieList = movieService.getAllMovies();

        ModelAndView modelAndView = new ModelAndView("home_template");
        modelAndView.addObject("peliculas",movieList);

        return modelAndView;

    }
}
