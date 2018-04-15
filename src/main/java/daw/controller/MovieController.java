package daw.controller;


import daw.models.Movie;
import daw.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MovieController {


    private final MovieService movieService;
    Movie movie;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //@Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping("/watch_movie")
    public ModelAndView watchMovie(@RequestParam long id) {
        movie = movieService.getMovie(id);
        return new ModelAndView("watch_movie_template").addObject("pelicula", movie);

    }

    //    @Secured("ROLE_ADMIN")
    @RequestMapping("/manage_movies")
    public ModelAndView manageUsers() {
        Iterable<Movie> listMovies = movieService.getAllMovies(); //list of users
        return new ModelAndView("manage_movies").addObject("movies", listMovies);
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping("/new_movie")
    public ModelAndView newMovie() {
        return new ModelAndView("new_movie");
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/insert_movie", method = { RequestMethod.GET, RequestMethod.POST })
    public String insertMovie(
            @RequestParam String movie_name,
            @RequestParam String url_movie,
            @RequestParam String description,
            @RequestParam(required = false) String year,
            @RequestParam String director,
            @RequestParam String actors,
            @RequestParam String genre,
            @RequestParam String url_cover_film,
            @RequestParam(required = false) String rating,
            RedirectAttributes redirectAttributes
    ) {

        movieService.addMovie(new Movie(url_cover_film,movie_name,year,director,description,url_movie,genre,actors,rating));

        redirectAttributes.addFlashAttribute("message", "Película ingresada correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "create");
        return "redirect:manage_movies";
    }


    // @Secured("ROLE_ADMIN")
    @RequestMapping("/edit_movie")
    public ModelAndView editMovie(@RequestParam long id) {
        movie = movieService.getMovie(id);
        return new ModelAndView("edit_movie_template").addObject("movies", movie);
    }

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update_movie", method = { RequestMethod.GET, RequestMethod.POST })
    public String updateMovie(
            @RequestParam long id,
            @RequestParam String movie_name,
            @RequestParam String url_movie,
            @RequestParam String description,
            @RequestParam(required = false) String year,
            @RequestParam String director,
            @RequestParam String actors,
            @RequestParam String genre,
            @RequestParam String url_cover_film,
            @RequestParam(required = false) String rating,
            RedirectAttributes redirectAttributes
    )
    {
        movieService.actualizarMovie(id,url_cover_film,movie_name,year,director,description,url_movie,genre,actors,rating);
        redirectAttributes.addFlashAttribute("message", "Película actualizada correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "update");
        return "redirect:/manage_movies";
    }



    //@Secured("ROLE_ADMIN")
    @RequestMapping("/remove_movie")
    public ModelAndView warningUser(@RequestParam long id) {
        movie = movieService.getMovie(id);
        return new ModelAndView("remove_movie").addObject("movies", movie);
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_movie", method = { RequestMethod.GET, RequestMethod.POST })
    public String deleteUser(@RequestParam long id, RedirectAttributes redirectAttributes) {

        movieService.removeMovie(id);

        redirectAttributes.addFlashAttribute("message", "Pelicula eliminado correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "delete");
        return "redirect:/manage_movies";
    }
}