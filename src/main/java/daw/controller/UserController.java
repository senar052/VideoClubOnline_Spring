package daw.controller;

import daw.models.User;
import daw.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {


    User user;

    @Autowired
    private UserService userService;


    // @Secured("ROLE_ADMIN")
    @RequestMapping("/manage_users")
    public ModelAndView manageUsers() {
        List<User> listUsers = userService.getAllUser(); //list of users
        return new ModelAndView("manage_users").addObject("users", listUsers);
    }


    //@Secured("ROLE_ADMIN")
    @RequestMapping("/new_user")
    public ModelAndView newUser() {
        return new ModelAndView("new_user");
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/insert_user", method = { RequestMethod.GET, RequestMethod.POST })
    public String insertUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             // @RequestParam String role,
                             RedirectAttributes redirectAttributes
    ) {

        userService.addUser(new User(username, password, email) );

        redirectAttributes.addFlashAttribute("message", "Usuario ingresado correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "create");
        return "redirect:/manage_users";
    }


    // @Secured("ROLE_ADMIN")
    @RequestMapping("/edit_user")
    public ModelAndView editUser(@RequestParam long id) {
        user = userService.getUser(id);
        return new ModelAndView("edit_user").addObject("users", user);
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update_user", method = { RequestMethod.GET, RequestMethod.POST })
    public String updateUser(
            @RequestParam long id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            RedirectAttributes redirectAttributes
    ) {

        userService.actualizarUser(id, username, password, email);

        redirectAttributes.addFlashAttribute("message", "Usuario actualizado correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "update");
        return "redirect:/manage_users";
    }


    //@Secured("ROLE_ADMIN")
    @RequestMapping("/remove_user")
    public ModelAndView warningUser(@RequestParam long id) {
        user = userService.getUser(id);
        return new ModelAndView("remove_user").addObject("users", user);
    }

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/delete_user", method = { RequestMethod.GET, RequestMethod.POST })
    public String deleteUser(@RequestParam long id, RedirectAttributes redirectAttributes) {

        userService.removeUser(id);

        redirectAttributes.addFlashAttribute("message", "Usuario eliminado correctamene!");
        redirectAttributes.addFlashAttribute("type_message", "delete");
        return "redirect:/manage_users";
    }


}