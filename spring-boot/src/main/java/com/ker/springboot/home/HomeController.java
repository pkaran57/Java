package com.ker.springboot.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "home";   /* This value is interpreted as the logical name of a view. The template name is derived from the logical view name by prefixing it with
                            /templates/ and postfixing it with .html. The resulting path for the template is /templates/home.html*/
    }
}
