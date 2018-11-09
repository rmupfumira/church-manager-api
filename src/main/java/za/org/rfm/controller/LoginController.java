package za.org.rfm.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import za.org.rfm.entity.User;
import za.org.rfm.service.AuthService;
import za.org.rfm.service.impl.AuthServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    AuthService authService;

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title","RFM Finance Dashboard");
        return "index";
    }


    @PostMapping("login")
    public ResponseEntity<User> doLogin(@RequestBody User user) {

       if(StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())){
           User userFromDB = authService.doLogin(user);
           if(userFromDB != null){
               return ResponseEntity.status(HttpStatus.OK)
                       .body(userFromDB);
           }
       }
      return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("create")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if(StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword())){
            authService.addUser(user);
            return ResponseEntity.ok("User "+user.getFullName()+" created");
        }
        return ResponseEntity.badRequest().body(null);
    }
}
