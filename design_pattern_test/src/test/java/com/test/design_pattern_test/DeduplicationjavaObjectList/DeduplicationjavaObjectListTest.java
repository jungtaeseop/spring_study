package com.test.design_pattern_test.DeduplicationjavaObjectList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;

import static com.test.design_pattern_test.DeduplicationjavaObjectList.DeduplicationUtils.deduplication;

@SpringBootTest
class DeduplicationjavaObjectListTest {
    private static final Logger logger = Logger.getLogger(DeduplicationjavaObjectListTest.class.getName());
    @Test
    void mainTest() {
        StudentInfo s1 = new StudentInfo(1, "홍길동", 15);
        StudentInfo s2 = new StudentInfo(2, "김길동", 12);
        StudentInfo s3 = new StudentInfo(3, "박길동", 18);
        StudentInfo s4 = new StudentInfo(4, "이길동", 21);
        StudentInfo s5 = new StudentInfo(5, "홍길동", 16);
        StudentInfo s6 = new StudentInfo(6, "최길동", 11);

        List<StudentInfo> list = List.of(s1, s2, s3, s4, s5, s6);

        // 이름이 같은 사람은 제거
        List<StudentInfo> distinct = deduplication(list, StudentInfo::getName);

        // 학번이 5인 홍길동이 제거 되었으니 size 는 5
        Assertions.assertEquals(5, distinct.size());


        distinct.forEach(studentInfo -> logger.info("test : {}"+studentInfo.toString()));
    }
}