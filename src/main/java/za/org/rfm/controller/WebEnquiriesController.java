package za.org.rfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import za.org.rfm.entity.Guest;

@Controller
@RequestMapping("api")
public class WebEnquiriesController {

    @PostMapping("contactus")
    public void contactUs(@RequestBody Guest guest){
        System.out.println("we have made contact "+guest.getName());
    }

}
