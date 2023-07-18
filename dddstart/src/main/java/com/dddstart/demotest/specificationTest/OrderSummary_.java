package com.dddstart.demotest.specificationTest;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(OrderSummary.class)
public class OrderSummary_ {
    public static volatile SingularAttribute<OrderSummary, String> number;
    public static volatile SingularAttribute<OrderSummary, Long> version;
    public static volatile SingularAttribute<OrderSummary, String> ordererld;
    public static volatile SingularAttribute<OrderSummary, String> ordererName;
    public static volatile SingularAttribute<OrderSummary, LocalDateTime> orderDate;
}
