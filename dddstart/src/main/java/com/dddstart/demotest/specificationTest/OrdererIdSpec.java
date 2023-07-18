package com.dddstart.demotest.specificationTest;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OrdererIdSpec implements Specification<OrderSummary>{
    private String ordererId;

    public OrdererIdSpec(String ordererId){
        this.ordererId = ordererId;
    }

    @Override
    public Predicate toPredicate(Root<OrderSummary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.equal(root.get(OrderSummary_.ordererld),ordererId);
    }
}
