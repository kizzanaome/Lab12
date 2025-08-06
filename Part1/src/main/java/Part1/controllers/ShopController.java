package Part1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    @GetMapping("/shop")
    public ResponseEntity<?> shop(){
       return new ResponseEntity<String> ("Welcome to my shop", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public  ResponseEntity<?> orders(){
        return  new ResponseEntity<String>("All Orders ", HttpStatus.OK);

    }

    @GetMapping("/payments")
    public ResponseEntity<?> payments(){
        return new ResponseEntity<String>("Finance: All Payments ", HttpStatus.OK);
    }

}
