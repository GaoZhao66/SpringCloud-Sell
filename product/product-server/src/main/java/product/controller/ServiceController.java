package product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {


    @RequestMapping("/msg")
    public String msg(){
        return "this is product Service";
    }


}
