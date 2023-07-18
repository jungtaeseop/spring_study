package com.dddstart.demotest.mappingImplementation.valueCollectionOneColumn;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emailSet;

    @Builder(builderMethodName = "createUser")
    public User(Long id, EmailSet emailSet){
        this.id = id;
        this.emailSet = emailSet;
    }
}
