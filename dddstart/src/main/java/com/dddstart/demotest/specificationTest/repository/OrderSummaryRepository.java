package com.dddstart.demotest.specificationTest.repository;

import com.dddstart.demotest.specificationTest.OrderSummary;
import org.springframework.data.repository.Repository;

public interface OrderSummaryRepository extends Repository<OrderSummary,Long>, OrderSummaryRepositoryCustom{
}
