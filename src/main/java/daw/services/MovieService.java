package daw.services;

import daw.models.Movie;
import daw.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    Movie movie;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieTitulo(String titulo){
        return movieRepository.findByTituloLike(titulo);
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void removeMovie(Long id) { movieRepository.delete(id);}


    public Movie getMovie(Long id) { return movieRepository.findOne(id);}

    public void actualizarMovie(Long id, String urlImagen, String titulo, String year, String director, String sinopsis, String urlVideo, String genero, String reparto, String valoracion){
        movie = movieRepository.findOne(id);
        movie.setUrlImagen(urlImagen);
        movie.setTitulo(titulo);
        movie.setYear(year);
        movie.setDirector(director);
        movie.setSinopsis(sinopsis);
        movie.setReparto(reparto);
        movie.setGenero(genero);
        movie.setUrlVideo(urlVideo);
        movie.setValoracion(valoracion);

        movieRepository.save(movie);
    }
}
