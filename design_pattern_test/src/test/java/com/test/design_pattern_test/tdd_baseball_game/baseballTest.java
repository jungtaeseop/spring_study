package com.test.design_pattern_test.tdd_baseball_game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class baseballTest {



    @Test
    public void 야구게임_1_9까지_체크(){

        String input = "123";


        assertThat(true).isEqualTo(Baseball.vaildationNumber(input));
    }

}