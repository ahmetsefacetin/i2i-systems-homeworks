package com.example.springUI.controller;

import com.example.springUI.model.Subscriber;
import com.example.springUI.service.SubscriberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubscriberController {

    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/subscriber")
    public String showForm(Model model) {
        model.addAttribute("subscriber", new Subscriber());
        return "subscriber-form";
    }

    @PostMapping("/subscriber")
    public String saveSubscriber(@Valid @ModelAttribute Subscriber subscriber, BindingResult result) {
        if (result.hasErrors()) {
            return "subscriber-form";
        }
        subscriberService.createSubscriber(subscriber);
        return "redirect:/subscriber";
    }

}
