package com.test.design_pattern_test.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StringtoObjectMapperTest {

    @Autowired
    private StringtoObjectMapper stringtoObjectMapper;

    @Test
    void change() throws JsonProcessingException {
        stringtoObjectMapper.change();
    }
}