package com.dddstart.demotest.specificationTest;

import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public interface Specification<T> extends Serializable {

    default Specification<T> and(@Nullable Specification<T> other){
        return (other == null) ? this : (root, query, cb) -> cb.and(toPredicate(root, query, cb), other.toPredicate(root, query, cb));
    }
    default Specification<T> or(@Nullable Specification<T> other){
        return (other == null) ? this : (root, query, cb) -> cb.or(toPredicate(root, query, cb), other.toPredicate(root, query, cb));
    }

    @Nullable
    Predicate toPredicate(Root<T> root,
                          CriteriaQuery<?> query,
                          CriteriaBuilder cb);
}
