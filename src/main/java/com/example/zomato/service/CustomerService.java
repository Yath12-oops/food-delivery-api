package com.example.zomato.service;

import com.example.zomato.convertors.CustomerConvertor;
import com.example.zomato.dto.request.CustomerRequest;
import com.example.zomato.dto.response.CustomerResponse;
import com.example.zomato.exceptions.CustomerNotFound;
import com.example.zomato.models.Customer;
import com.example.zomato.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer= CustomerConvertor.customerRequestToCustomer(customerRequest);
        Customer savedCustomer=customerRepo.save(customer);
        return CustomerConvertor.customerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse updateCustomer(int customerId, CustomerRequest customerRequest) {
        Optional<Customer> optional=customerRepo.findById(customerId);

        if(optional.isEmpty()){
            throw new CustomerNotFound("Invalid Customer Id "+ customerId);
        }

        Customer customer=optional.get();

        customer.setAddress(customerRequest.getAddress());
        customer.setEmail(customerRequest.getEmail());
        customer.setMobNo(customerRequest.getMobNo());

        Customer savedCustomer=customerRepo.save(customer);

        return CustomerResponse.builder()
                .name(savedCustomer.getName())
                .message("Details Updated Succesfully")
                .build();
    }
}
