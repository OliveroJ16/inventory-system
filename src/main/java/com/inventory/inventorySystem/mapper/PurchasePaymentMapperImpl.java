package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.PurchasePaymentRequest;
import com.inventory.inventorySystem.dto.response.PurchasePaymentResponse;
import com.inventory.inventorySystem.mapper.interfaces.PurchasePaymentMapper;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.PurchasePayment;
import org.springframework.stereotype.Component;

@Component
public class PurchasePaymentMapperImpl implements PurchasePaymentMapper {

    @Override
    public PurchasePayment toEntity(PurchasePaymentRequest request, Purchase purchase) {
        var payment = new PurchasePayment();
        payment.setAmountPaid(request.amountPaid());
        payment.setPaymentType(request.paymentType());
        payment.setPurchase(purchase);
        return payment;
    }

    @Override
    public PurchasePaymentResponse toDto(PurchasePayment payment) {
        return new PurchasePaymentResponse(
                payment.getId(),
                payment.getPaymentDate(),
                payment.getAmountPaid(),
                payment.getPaymentType(),
                payment.getPurchase().getId()
        );
    }
}
