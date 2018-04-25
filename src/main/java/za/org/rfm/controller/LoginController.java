package za.org.rfm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title","RFM Finance Dashboard");
        return "index.html";
    }


    @GetMapping("login")
    public ResponseEntity<String> getUser() {

        return new ResponseEntity<String>("Done!", HttpStatus.OK);
    }
}
