package com.example.o2o.util;

import lombok.Data;

import java.util.Date;
import java.util.List;

public class JsonUtils {

    @Data
    static class User {
        private String userName;
        private Integer userAge;
        private Date userBirthday;
        private List<String> userDesc;
    }



}


























