package daw.services;


import daw.models.User;
import daw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    User user;
    /*


    //Por que no puede ser List en vez de Iterable¿?


     */



    public User getUser(Long id){
        return userRepository.findOne(id);
    }

    public List<User> getAllUser() { return userRepository.findAll();}



    public User getUserName(String name){
        return userRepository.findByNameLike(name);
    }

    public User getUserEmail(String email){ return userRepository.findByEmailLike(email); }


    public void addUser(User user1){ userRepository.save(user1);  }


    public void removeUser(Long id) {
        user = userRepository.findOne(id);
        userRepository.delete(user);
    }


    public  void actualizarUser(Long id, String nombre, String pass, String email){
        user = userRepository.findOne(id);
        user.setName(nombre);
        user.setEmail(email);
        user.setPass(pass);

        userRepository.save(user);
    }



}