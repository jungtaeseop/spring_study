package com.onetoone.onetoonetest.onetoone;

import javax.persistence.*;

@Entity
public class PersonProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bodyname;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
