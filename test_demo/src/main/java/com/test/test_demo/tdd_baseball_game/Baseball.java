package com.test.test_demo.tdd_baseball_game;

import java.util.Scanner;

public class Baseball {
    public static void main (String[] args){
        System.out.print("숫자를 입력해 주세요 : ");

        Scanner in = new Scanner(System.in);



        int inputNumber = in.nextInt();
        System.out.println("inputNumber "+inputNumber);



    }

    public static boolean vaildationNumber(String input) {
        input.split("");

        return true;
    }
}
