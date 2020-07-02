package com.example.springboot_o2o.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonUtils {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();

        user.setUserName("ky");
        user.setUserAge(23);
        user.setUserBirthday(new Date());
        List<String> desc = new ArrayList<>();
        desc.add("huya");
        desc.add("guangzhou");
        desc.add("sysu");
        user.setUserDesc(desc);

        String userJson = mapper.writeValueAsString(user);

        System.out.println(userJson);

        String jsonStr = "{\"userName\":\"ky\",\"userAge\":23,\"userBirthday\":1591690609470,\"userDesc\":[\"huya\",\"guangzhou\",\"sysu\"]}";

        User newUser = mapper.readValue(jsonStr, User.class);

        System.out.println(newUser.toString());
        System.out.println(newUser.getUserName());
        System.out.println(newUser.getUserAge());
        System.out.println(newUser.getUserBirthday());
        System.out.println(newUser.getUserDesc().toString());
    }

    @Data
    static class User {
        private String userName;
        private Integer userAge;
        private Date userBirthday;
        private List<String> userDesc;
    }



}


























