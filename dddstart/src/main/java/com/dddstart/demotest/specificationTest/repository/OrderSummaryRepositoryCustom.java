package com.dddstart.demotest.specificationTest.repository;

import com.dddstart.demotest.specificationTest.OrderSummary;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrderSummaryRepositoryCustom {
    public List<OrderSummary> searchOrderSummaries(Specification<OrderSummary> spec);
}
