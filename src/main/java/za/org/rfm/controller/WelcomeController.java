package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;
import za.org.rfm.entity.Guest;
import za.org.rfm.entity.Assembly;
import za.org.rfm.service.AssemblyService;
import za.org.rfm.service.EmailService;

@RestController
public class WelcomeController {

    @Autowired
    private AssemblyService assemblyService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "Church manager Api Working!";
    }
    @PostMapping("/guest")
    public void sendWebsiteEnquiryEmail(@RequestBody Guest guest){
        //emailService.sendWebSiteEmail(guest);

    }

}

