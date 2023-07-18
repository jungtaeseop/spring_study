package com.test.test_demo.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StringtoObjectMapper {

    public void change() throws JsonProcessingException {
        String str = "{\"openOperation\":{\"1\":{\"color\":\"red\",\"text\":\"개방모드\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"seasonCarOnly\":{\"1\":{\"color\":\"red\",\"text\":\" 서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"greenCar\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"counter\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"seasonCar\":{\"1\":{\"color\":\"red\",\"text\":\"서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"sportsCenter\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"noDrivingSystem\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"oneWay\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"noOperation\":{\"1\":{\"color\":\"red\",\"text\":\" 서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"운영중지중\",\"type\":\"text\"}},\"visitorOnly\":{\"1\":{\"color\":\"red\",\"text\":\" 서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"bookCar\":{\"1\":{\"color\":\"red\",\"text\":\"예약차량\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"unpaidCar\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"bookCarOnly\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"member\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"blackList\":{\"1\":{\"color\":\"red\",\"text\":\"블랙리스트\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"noData\":{\"1\":{\"color\":\"red\",\"text\":\"번호인식실패\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"seasonCarInOther\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"basic\":{\"1\":{\"color\":\"red\",\"text\":\"공용차량전용\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"일반차량금지\",\"type\":\"text\"}},\"visitor\":{\"1\":{\"color\":\"red\",\"text\":\"서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"\",\"type\":\"inCarNo\"}},\"visitingCar\":{\"1\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"},\"2\":{\"color\":\"black\",\"text\":\"\",\"type\":\"text\"}},\"full\":{\"1\":{\"color\":\"red\",\"text\":\"서울대학교\",\"type\":\"text\"},\"2\":{\"color\":\"green\",\"text\":\"만차입니다\",\"type\":\"text\"}}}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Map<String, Map<String, Object>>> mapper = objectMapper.readValue(str, new TypeReference<Map<String,Map<String,Map<String, Object>>>>() {});
        System.out.println(mapper.get("openOperation").get("1"));


    }
}
