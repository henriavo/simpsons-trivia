package com.example.validatingforminput;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

    @Autowired
    private Environment env;

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
        return "results";
    }

    @GetMapping("/feedback")
    public String renderFeedback(@ModelAttribute @Valid  FeedbackForm feedbackForm, Model model){
        model.addAttribute("feedbackForm", feedbackForm);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String submitFeedback(@ModelAttribute @Valid FeedbackForm feedbackForm){
        System.out.println("inside submitFeedback()");
        String content = feedbackForm.getContent();
        System.out.println("captured feedback string: " + content);

        String mongoUrl =  env.getProperty("mongodb.uri");
        System.out.println("returned mongoUrl from application.properties: " + mongoUrl);

        try (MongoClient mongoClient = MongoClients.create(mongoUrl)) {

            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("simpsons_trivia");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("feedback");

            insertOneDocument(gradesCollection, content);
        }

        return "redirect:/";
    }

    private static void insertOneDocument(MongoCollection<Document> feedbackCollection, String userFeedback) {
        feedbackCollection.insertOne(new Document("type", "feedback").append("feedback_string", userFeedback));
        System.out.println("One feedback inserted.");
    }

    @PostMapping("/thanks")
    public String showThanks() {
        return "redirect:/thanks";
    }
}