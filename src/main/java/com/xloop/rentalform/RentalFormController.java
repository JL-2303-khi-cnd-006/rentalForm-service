package com.xloop.rentalform;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rentalForm")
public class RentalFormController {

    private IRentalForm repo;

    public RentalFormController(IRentalForm repo){
        this.repo= repo;
    }
    
    @PostMapping("/add")
    public String addForm(@RequestBody RentalForm rentalForm){
        repo.save(rentalForm);
        return "New form has been Added";
    }

    @GetMapping("/getList")
    public List<RentalForm> getAllCars(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<RentalForm> getTheFormById(@PathVariable long id ){
        return repo.findById(id);
    }
}
