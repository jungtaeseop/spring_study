package com.onetoone.onetoonetest.onetoone;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PersonRepository {
    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    public PersonRepository(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


}
