package com.dddstart.demotest.specificationTest.repository;

import com.dddstart.demotest.specificationTest.OrderSummary;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderSummaryRepositoryImpl implements OrderSummaryRepositoryCustom{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<OrderSummary> searchOrderSummaries(Specification<OrderSummary> spec) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderSummary> query = cb.createQuery(OrderSummary.class);
        Root<OrderSummary> root = query.from(OrderSummary.class);

        query.select(root).where(spec.toPredicate(root, query, cb));

        return em.createQuery(query).getResultList();
    }
}
