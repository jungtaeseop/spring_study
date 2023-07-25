package com.test.test_demo.tdd_baseball_game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class baseballTest {
    @Test
    public void 야구게임_1_9까지_체크(){

        int input = 123;

        assertThat(input).isEqualTo(123);
    }

}