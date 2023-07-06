package com.dddstart.demotest.mappingImplementation.valueCollectionOneColumn;

import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emailSet;
}
