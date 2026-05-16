package com.example.zomato.controller;

import com.example.zomato.dto.request.DeliveryPartnerRequest;
import com.example.zomato.dto.response.DeliveryPartnerResponse;
import com.example.zomato.service.DeliveryPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/partner")
@RequiredArgsConstructor
public class DeliveryPartnerController {

    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        DeliveryPartnerResponse response=deliveryPartnerService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateDeliveryPartner(@RequestParam("id") int deliveryPartnerId,@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        try{
            DeliveryPartnerResponse response=deliveryPartnerService.updateDeliveryPartner(deliveryPartnerId,deliveryPartnerRequest);
            return new ResponseEntity(response,HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
