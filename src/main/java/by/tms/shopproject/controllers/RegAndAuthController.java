package by.tms.shopproject.controllers;

import by.tms.shopproject.entity.User;
import by.tms.shopproject.entity.UserDTO;
import by.tms.shopproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class RegAndAuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/reg")
    public ModelAndView getRegPage(ModelAndView modelAndView){
        modelAndView.addObject("ModelAttribute", new User());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping(path = "/reg")
    public ModelAndView postRegPage(@Valid @ModelAttribute("ModelAttribute")User user, BindingResult bindingResult,
                                    ModelAndView modelAndView){
        modelAndView.setViewName("reg");
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/reg");
        }
        if(!userRepository.existsUserByLogin(user.getLogin())) {
            userRepository.save(user);
            modelAndView.setViewName("redirect:/auth");
        } else {
            modelAndView.setViewName("redirect:/reg");
        }
        return modelAndView;
    }

    @GetMapping(path = "/auth")
    public ModelAndView getAuthPage(ModelAndView modelAndView){
        modelAndView.addObject("ModelAttribute", new UserDTO());
        modelAndView.setViewName("auth");
        return modelAndView;
    }

    @PostMapping(path = "/auth")
    public ModelAndView postAuthPage(@Valid @ModelAttribute("ModelAttribute")UserDTO userDTO,
                                     ModelAndView modelAndView, BindingResult bindingResult,
                                     HttpSession httpSession){
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/auth");
        } else {
            httpSession.setAttribute("ModelAttribute", userDTO);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

//    @GetMapping(path = "/auth?error")
//    public ModelAndView getAuthErrorPage(ModelAndView modelAndView){
//        modelAndView.setViewName("auth");
//        return modelAndView;
//    }


}
