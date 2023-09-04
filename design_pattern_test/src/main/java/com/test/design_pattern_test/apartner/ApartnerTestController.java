package com.test.design_pattern_test.apartner;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class ApartnerTestController {

    @PostMapping("/nexpa/mdl")
    public String apartnerTest(@RequestBody String body) throws JsonProcessingException {

        log.info("body : {}",body);
        String result = null;

        JSONObject json = new JSONObject(body);
        String command = String.valueOf(json.get("command"));

        switch(command){
            case "visit_check2":
                result = visitCheck(body);
                break;
        }

        return result;
    }

    private String visitCheck(String body) throws JsonProcessingException {
        String  result = "";
        HashMap bodyMap = new HashMap();
        HashMap resultMap = new HashMap();
        HashMap dataMap = new HashMap();

        JSONObject json = new JSONObject(body);
        Gson gson = new Gson();


        HashMap<String,String> condition = gson.fromJson(String.valueOf(json.get("data")),new TypeToken<HashMap<String,String>>() {}.getType());

        condition.get("reg_no");
        condition.get("car_number");
        condition.get("unit_code");
        condition.get("park_no");


        resultMap.put("status",200);
        resultMap.put("message","OK");

        dataMap.put("check_yon","Y");
        dataMap.put("dong","");
        dataMap.put("ho","");
        dataMap.put("remark","");
        dataMap.put("unit_code",condition.get("unit_code"));
        dataMap.put("reg_no",condition.get("reg_no"));
        dataMap.put("car_number",condition.get("car_number"));


        bodyMap.put("command","visit_check2");
        bodyMap.put("result",resultMap);
        bodyMap.put("data",dataMap);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        result = mapper.writeValueAsString(bodyMap);

        return result;
    }
}
