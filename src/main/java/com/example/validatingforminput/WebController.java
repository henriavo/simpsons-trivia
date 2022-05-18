package com.example.validatingforminput;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
public class WebController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
        registry.addViewController("/thanks").setViewName("thanks");
    }

    @GetMapping("/")
    public String showForm(@ModelAttribute TriviaForm triviaForm, HttpServletRequest request, HttpServletResponse response,
                           @CookieValue(name="simpsons-win-streak", required=false) String cookie) {

        int count = 0;
        if (request.getCookies() != null){
            count =  request.getCookies().length;
        }

        System.out.println("request cookie count: " + count);

        if(cookie == null){
            Cookie cc = new Cookie("simpsons-win-streak", "0");
            response.addCookie(cc);
        }

        return "form";
    }

    @PostMapping("/")
    public ModelAndView checkPersonInfo(@ModelAttribute TriviaForm triviaForm, HttpServletRequest request,
                                        HttpServletResponse response,
                                        @CookieValue(name="simpsons-win-streak", required=true) String cookie) {

        ModelAndView resultModelAndView = new ModelAndView("results");
        resultModelAndView.addObject(triviaForm);

        ModelAndView formModelAndView = new ModelAndView("form");
        formModelAndView.addObject("triviaForm", triviaForm);

        System.out.println("triviaForm= " + triviaForm);

        if (triviaForm.allCorrect()){
            int newValue = Integer.parseInt(cookie) + 1;
            System.out.println("logging newValue= " + newValue);

            Cookie cc = new Cookie("simpsons-win-streak", String.valueOf(newValue));
            response.addCookie(cc);

            resultModelAndView.addObject("simpsonswinstreak", String.valueOf(newValue));

            return resultModelAndView;
        }

        else
            return formModelAndView;
    }

    @GetMapping("/error")
    public String showError() {
        return "error";
    }

    @GetMapping("/results")
    public String results(@ModelAttribute @Valid TriviaForm triviaForm, Model model) {
        model.addAttribute("triviaform", triviaForm);
        System.out.println("hello from results() method *******"  + triviaForm.getName());
        return "results";
    }

    @PostMapping("/thanks")
    public String showThanks() {
        return "redirect:/thanks";
    }
}