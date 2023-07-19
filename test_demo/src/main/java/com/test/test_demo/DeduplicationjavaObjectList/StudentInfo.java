package com.test.test_demo.DeduplicationjavaObjectList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentInfo {
    private long studentNo;

    private String name;

    private int age;

    public StudentInfo(long studentNo, String name, int age) {
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
    }


}
