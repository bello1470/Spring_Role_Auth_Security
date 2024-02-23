package org.bellotech.springSecurity.controller;


import org.bellotech.springSecurity.model.OurUserModel;
import org.bellotech.springSecurity.model.ProductModel;
import org.bellotech.springSecurity.repository.OurProductRepo;
import org.bellotech.springSecurity.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    private OurUserRepo ourUserRepo;

    @Autowired
    private OurProductRepo ourProductRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public  String goHome(){

        return "Home Pages";
    }

    @PostMapping("/users/save")
    public ResponseEntity<Object> saveUser (@RequestBody OurUserModel ourUserModel){
        ourUserModel.setPassword(passwordEncoder.encode(ourUserModel.getPassword()));
        OurUserModel result = ourUserRepo.save(ourUserModel);
        if (result.getId() > 0){
            return ResponseEntity.ok("User Was Saved");
        }
        return ResponseEntity.status(404).body("User Not Save");
    }
    @PostMapping("/product/add")
    public ResponseEntity<Object> addProduct (@RequestBody ProductModel productModel){

            ProductModel result = ourProductRepo.save(productModel);
        if (result.getId() > 0){
            return ResponseEntity.ok("Product Was Added");
        }
        return ResponseEntity.status(404).body("Product Not Save");
    }
    @GetMapping("/products/all")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(ourProductRepo.findAll());
    }
    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(ourUserRepo.findAll());

    }
    @GetMapping("/users/single")
    @PreAuthorize("hasAuthrity('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(ourUserRepo.findByEmail(getLoggedInUserDetails().getUsername()));


    }

    public UserDetails getLoggedInUserDetails(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
