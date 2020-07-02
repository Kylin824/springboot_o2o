package com.example.springboot_o2o.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

/**
 * @author kylin
 * @create 2020/6/9 19:53
 */
public class Md5Utils {

    public static void main(String[] args) throws IOException {
//        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////
////        Long clickTime = 1591718394424L;
////        int days = Period.between(activationDate.toLocalDate(), LocalDate.now()).getDays();
////        System.out.println(days);
//        LocalDateTime activationDate = LocalDateTime.parse("2020-06-10 22:52:47", DATE_TIME_FORMATTER);
//        LocalDateTime nowDate = LocalDateTime.parse("2020-06-12 23:19:56", DATE_TIME_FORMATTER);
//        LocalDateTime dateTime =LocalDateTime.ofEpochSecond(1591718281L,0, ZoneOffset.ofHours(8));
////        LocalDateTime activationDate = LocalDateTime.parse(DATE_TIME_FORMATTER.format(dateTime), DATE_TIME_FORMATTER);
//        System.out.println(activationDate);
////        int days = Period.between(activationDate.toLocalDate(), LocalDate.now()).getDays();
//        int days = Period.between(activationDate.toLocalDate(), nowDate.toLocalDate()).getDays();
//        System.out.println(days);
////        String OCPC_ACTIVATION_MID_KEY_PATTER="OCPC_BAIDU_FEED_ACTIVATION_%s";
////        String OCPC_KEY_PATTERN = "OCPC_%s";
////        String ip = "111.18.183.14";
////        String os = "Android9";
////        String machine = "PCHM30";
////
////        if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(os) && StringUtils.isNotBlank(machine)){
////            String key = String.format(OCPC_KEY_PATTERN,("OCPC_QQ_MARKETING"+"_"+ip+"_"+os+"_"+machine).toUpperCase());
////            System.out.println(key);
////        }
//
//
        String ip = "112.90.123.221";
        Parser uaParser = new Parser();
        Client client = uaParser.parse("Mozilla/5.0 (Linux; U; Android 8.1.0; zh-cn; PBAM00 Build/OPM1.171019.026) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/70.0.3538.80 Mobile Safari/537.36 HeyTapBrowser/10.7.4.2");
        String osVersion = UserAgentParser.osFormat(client);
        String device = client.device.family;
        System.out.println(ip + " " + osVersion + " " +  device);
////
////        String OCPC_KEY_PATTERN="OCPC_%s";
////
////        if (StringUtils.isNotBlank(ip) && StringUtils.isNotBlank(osVersion) && StringUtils.isNotBlank(device)) {
////            String key = String.format(OCPC_KEY_PATTERN, ("OCPC_QQ_MARKETING" + "_" + ip + "_" + osVersion + "_" + device).toUpperCase());
////            System.out.println(key);
////        }
//
////        String mac = DigestUtils.md5Hex("44:59:E3:1F:BD:5E".toUpperCase().replace(":",""));
////        System.out.println(mac);
////        String mac1 = DigestUtils.md5Hex("44:59:E3:1F:BD:5E".toUpperCase());
////        System.out.println(mac1);
////        System.out.println(DigestUtils.md5Hex("869000043797646").toLowerCase().toLowerCase());
////
////        String muid = "cda60d69d37c26d8bbf9506d3db68509";
////        String imei = "869000043797646";
////
////        String OCPC_KEY_PATTERN = "OCPC_%s";
////
////        System.out.println(String.format(OCPC_KEY_PATTERN,muid));
////        System.out.println(String.format(OCPC_KEY_PATTERN, DigestUtils.md5Hex(imei.toLowerCase()).toLowerCase()));
//        OcpcParam param = new OcpcParam();
//        if (StringUtils.isNotBlank(param.getTraceId()))
//            System.out.println(DigestUtils.md5Hex(param.getTraceId()));
//        else
//            System.out.println("null");

//        System.out.println(DigestUtils.md5Hex("864688039436693"));

//        String channel = "a_1_guangdiantong00101";
//        String channel = "a_guangdiantong00101";
//        // a_baidusem00001
//        if(channel.startsWith("a_") && channel.length()>=7){
//
//            String prefix = channel.substring(2,channel.length()-5);
//            System.out.println(prefix);
////            adChannelType = adChannelTypeMap.get(prefix);
////
////            if(adChannelType!=null){
////                return adChannelType;
////            }
//
//            //兼容 a_guangdiantong_00001这种情况
//            if(prefix.contains("_")){
//                int end = prefix.indexOf("_");
//                prefix = prefix.substring(0,end);
//                System.out.println(prefix);
//            }
//
//        }

//        System.out.println(DigestUtils.md5Hex("ABCDE"));
        System.out.println(DigestUtils.md5Hex("02:00:00:00:00:00"));
        System.out.println(DigestUtils.md5Hex("02:00:00:00:00:00".replace(":","").toUpperCase()));
//        System.out.println(DigestUtils.md5Hex("bde6adfb-bfc1-be15-fffc-7f7fd6dfbac2"));
        System.out.println("mac===============");
        System.out.println(DigestUtils.md5Hex("00:00:00:00:00:00"));
        System.out.println(DigestUtils.md5Hex("00:00:00:00:00:00".replace(":","").toUpperCase()));

        System.out.println(DigestUtils.md5Hex("000000000000000"));
        System.out.println(DigestUtils.md5Hex("000000000000"));
        System.out.println(DigestUtils.md5Hex("00000000"));
        System.out.println(DigestUtils.md5Hex("0"));
        System.out.println(">>>>>>>>>>>>");
        System.out.println(DigestUtils.md5Hex("861229048671815"));
        System.out.println(DigestUtils.md5Hex("861229048671807"));
        System.out.println(DigestUtils.md5Hex("864708042504574"));
        System.out.println(DigestUtils.md5Hex("868803047980170"));
        System.out.println(DigestUtils.md5Hex("868803047980162"));

    }


    @Data
    public static class OcpcParam {
        private String traceId;
        private String userId;
        private String clickId;
        private String callbackUrl;
        private int channelType;
        private String channel;
        private Long ts;
        private Long clickTime;
        private String activationTime;
        private Boolean retention1Callbacked;

    }

}
