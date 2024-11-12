package com.example.kanoapiinterface;

import com.kano.kanoapiclientsdk.client.KanoApiClient;
import com.kano.kanoapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class KanoApiInterfaceApplicationTests {

    @Resource
    private KanoApiClient kanoApiClient;

    @Test
    void contextLoads() {
        String result = kanoApiClient.getNameByGet("kano");
        User user = new User();
        user.setName("kano");
        String userNameByPost = kanoApiClient.getUserNameByPost(user);
        System.out.println(result);
        System.out.println(userNameByPost);


    }

}
