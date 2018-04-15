package daw.bd;


import daw.models.Movie;
import daw.models.User;
import daw.repositories.UserRepository;
import daw.services.MovieService;
import daw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataBaseLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @PostConstruct
    private void baseDatos() {


        userService.addUser(new User("user", "user1", "user@upm.es"));

        userService.addUser(new User ("admin", "admin2","admin@upm.es"));


        /*

        GrantedAuthority[] adminRoles = { new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN") };
        userService.addUser(new User("admin", "admin2", "admin@upm.es", Arrays.asList(adminRoles)));

        GrantedAuthority[] userRoles = { new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_USER") };
        userService.addUser(new User("user", "user1", "user@upm.es", Arrays.asList(userRoles)));
*/
/*
        // User #1: "user", with password "p1" and role "USER"
        GrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
        userRepository.save(new User("user", "p1", "usr", Arrays.asList(userRoles)));
        // User #2: "root", with password "p2" and roles "USER" and "ADMIN"
        GrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN")};
        userRepository.save(new User("root", "p2", "dos", Arrays.asList(adminRoles)));
*/

        movieService.addMovie(new Movie("https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg",
                "batman", "2012", "Nolan","Batman vuelve",
                "https://www.youtube.com/embed/vak9ZLfhGnQ",
                "Accion","Robert","5 estrallas"));

        movieService.addMovie(new Movie("http://fusion-freak.es/wp-content/uploads/2017/04/la-momia-2017-poster-2.jpg",
                "The mummy", "2017", "Yo", "Una momia resucta",
                "https://www.youtube.com/embed/IjHgzkQM2Sg",
                "Accion, Aventura","Nick","3 estrellas"));

    }
}
