package com.example.o2o.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.InputStream;

@Data
@AllArgsConstructor
public class ImageHolder {
    String imgName;
    InputStream img;
}
