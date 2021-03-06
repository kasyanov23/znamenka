package ru.click.crm.controller.page;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Сережа on 14.08.2016.
 */
@Controller
@Slf4j
public class LoginController {

    @Value("${calendar.id}")
    private String calendarId;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("serviceEmail", calendarId);
        return "index";
    }

    @GetMapping("/")
    public View redirectToIndex() {
        return new RedirectView("/index");
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
