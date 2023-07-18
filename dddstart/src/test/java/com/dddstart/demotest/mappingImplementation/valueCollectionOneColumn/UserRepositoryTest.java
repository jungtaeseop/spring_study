package com.dddstart.demotest.mappingImplementation.valueCollectionOneColumn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Rollback(value = false)
    @Test
    public void userRepositorySave(){
        Email email = new Email("abc33@naver.com");
        Email email2 = new Email("abc3232@naver.com");

        Set<Email> emails = new HashSet<>();

        emails.add(email);
        emails.add(email2);

        EmailSet emailSet = new EmailSet(emails);

        User user = User.createUser()
                .emailSet(emailSet)
                .build();


        userServiceImpl.createUser(user);
    }

}