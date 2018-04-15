package daw.repositories;


import daw.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Movie findByTituloLike(@Param("titulo") String titulo);


}
