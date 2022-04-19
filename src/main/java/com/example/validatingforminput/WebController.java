package com.example.validatingforminput;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String showForm(TriviaForm triviaForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@ModelAttribute @Valid TriviaForm triviaForm,BindingResult bindingResult, Model model) {
        model.addAttribute("triviaform", triviaForm);
        System.out.println("triviaForm:::: " + triviaForm);

        if (triviaForm.allCorrect()){
            return "redirect:/results";
        }
        else
            return "form";
    }

    public boolean checkAnswers(TriviaForm form){
        return true;
//        if (!form.getStore().equalsIgnoreCase("apu")){
//            re
//        }
    }

    @GetMapping("/error")
    public String showError() {
        return "error";
    }

    @PostMapping("/thanks")
    public String showThanks() {
        return "redirect:/thanks";
    }
}