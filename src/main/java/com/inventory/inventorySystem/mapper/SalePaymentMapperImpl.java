package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.mapper.interfaces.SalePaymentMapper;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SalePayment;
import org.springframework.stereotype.Component;

@Component
public class SalePaymentMapperImpl implements SalePaymentMapper {

    @Override
    public SalePayment toEntity(SalePaymentRequest request, Sale sale) {
        var salePayment = new SalePayment();
        salePayment.setAmountPaid(request.amountPaid());
        salePayment.setPaymentType(request.paymentType());
        salePayment.setSale(sale);
        return salePayment;
    }

    @Override
    public SalePaymentResponse toDto(SalePayment salePayment) {
        return new SalePaymentResponse(
                salePayment.getId(),
                salePayment.getPaymentDate(),
                salePayment.getAmountPaid(),
                salePayment.getPaymentType(),
                salePayment.getSale().getId()
        );
    }
}
