package com.example.zomato.service;

import com.example.zomato.convertors.DeliveryPartnerConvertor;
import com.example.zomato.dto.request.DeliveryPartnerRequest;
import com.example.zomato.dto.response.DeliveryPartnerResponse;
import com.example.zomato.exceptions.DeliveryPartnerNotFound;
import com.example.zomato.models.DeliveryPartner;
import com.example.zomato.repository.DeliveryPartnerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryPartnerService {

    private final DeliveryPartnerRepo deliveryPartnerRepo;

    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner partner= DeliveryPartnerConvertor.deliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);
        DeliveryPartner saved=deliveryPartnerRepo.save(partner);
        return DeliveryPartnerConvertor.deliveryPartnerToDeliveryPartnerResponse(saved);
    }

    public DeliveryPartnerResponse updateDeliveryPartner(int deliveryPartnerId, DeliveryPartnerRequest deliveryPartnerRequest) {
        Optional<DeliveryPartner> optional=deliveryPartnerRepo.findById(deliveryPartnerId);

        if(optional.isEmpty()){
            throw  new DeliveryPartnerNotFound("Invalid Id "+deliveryPartnerId);
        }

        DeliveryPartner deliveryPartner=optional.get();

        deliveryPartner.setMobNo(deliveryPartnerRequest.getMobNo());
        deliveryPartner.setVehicleNo(deliveryPartnerRequest.getVehicleNo());

        DeliveryPartner saved=deliveryPartnerRepo.save(deliveryPartner);

        return DeliveryPartnerConvertor.deliveryPartnerToDeliveryPartnerResponse(saved);

    }
}
