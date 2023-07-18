package com.test.test_demo.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringtoObjectMapperTest {

    @Autowired
    private StringtoObjectMapper stringtoObjectMapper;

    @Test
    void change() throws JsonProcessingException {
        stringtoObjectMapper.change();
    }
}