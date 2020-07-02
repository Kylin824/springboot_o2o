package com.example.springboot_o2o.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UrlUtils {

    public static void main(String[] args) throws URISyntaxException, IOException {

//        System.out.println(UrlUtils.class.getClassLoader().getResource("url.txt"));
//        System.out.println(Paths.get(UrlUtils.class.getClassLoader().getResource("url.txt").toURI()));
        Files.readAllLines(Paths.get(UrlUtils.class.getClassLoader().getResource("url.txt").toURI()))
                .forEach(url-> {
                    MultiValueMap<String, String> parameters =
                            UriComponentsBuilder.fromUriString(url).build().getQueryParams();
                    try {
                        System.out.println(URLDecoder.decode(parameters.get("callback_url").get(0), StandardCharsets.UTF_8.name()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
    }
}
