package com.example.zomato.controller;

import com.example.zomato.dto.request.CustomerRequest;
import com.example.zomato.dto.response.CustomerResponse;
import com.example.zomato.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
     CustomerResponse response=customerService.addCustomer(customerRequest);
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateCustomer(@RequestParam("id") int customerId,@RequestBody CustomerRequest customerRequest){
        try{
            CustomerResponse response=customerService.updateCustomer(customerId,customerRequest);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
