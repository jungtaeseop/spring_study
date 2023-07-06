package com.onetoone.onetoonetest.onetoone;

import lombok.*;

import javax.persistence.*;



@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "person_name")
    private String name;


    @OneToOne(mappedBy = "person")
    private PersonProfile personProfile;


    public Person(String name,PersonProfile personProfile){
        this.name = name;
        this.personProfile = personProfile;
    }
}
