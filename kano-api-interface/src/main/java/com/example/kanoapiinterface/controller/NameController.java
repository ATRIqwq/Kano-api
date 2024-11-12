package com.example.kanoapiinterface.controller;



import com.kano.kanoapiclientsdk.model.User;
import com.kano.kanoapiclientsdk.util.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author yupi
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/json")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String body = request.getHeader("body");
        String nonce = request.getHeader("nonce");
        String timeStape = request.getHeader("timeStape");
        String sign = request.getHeader("sign");

        if (!accessKey.equals("kano")){
             throw new RuntimeException("无权限");
        }

        if (Long.parseLong(nonce) > 10000){
            throw new RuntimeException("无权限");
        }

        //todo 校验时间戳
//        if (){
//
//        }

        String genSign = SignUtil.genSign(body, "abcdefg");
        if (!sign.equals(genSign)){
            throw new RuntimeException("无权限");
        }


        return "POST 用户名字是" + user.getName();
    }
}

