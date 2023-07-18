package com.dddstart.demotest.specificationTest.service;

import com.dddstart.demotest.specificationTest.OrderSummary;
import com.dddstart.demotest.specificationTest.OrderSummarySpecs;
import com.dddstart.demotest.specificationTest.repository.OrderSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderSummaryServiceImpl {

    private final OrderSummaryRepository orderSummaryRepository;

    public List<OrderSummary> searchOrders(String ordererId, LocalDateTime from, LocalDateTime to) {

        Specification<OrderSummary> ordererIdSpec = OrderSummarySpecs.ordererId("userl");
        Specification<OrderSummary> orderDateBetweenSpec = OrderSummarySpecs.orderDateBetween(LocalDateTime.of(2022, 1, 1, 0, 0, 0),LocalDateTime.of(2022, 1, 2, 0, 0, 0));

        Specification<OrderSummary> combinedSpec = ordererIdSpec.and(orderDateBetweenSpec);

        return orderSummaryRepository.searchOrderSummaries(combinedSpec);
    }
}
