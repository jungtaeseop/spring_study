package com.dddstart.demotest.mappingImplementation.valueCollectionOneColumn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    public void createUser(User user){
        userRepository.save(user);
    }

}
