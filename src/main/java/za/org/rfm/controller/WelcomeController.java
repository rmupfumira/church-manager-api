package za.org.rfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import za.org.rfm.entity.Assembly;
import za.org.rfm.service.AssemblyService;

@Controller
public class WelcomeController {

    @Autowired
    private AssemblyService assemblyService;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        List<Assembly> assemblyList = assemblyService.getAllAssemblies();
        model.put("assemblyList", assemblyList);
        return "welcome";
    }

}

