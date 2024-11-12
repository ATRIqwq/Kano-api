package com.kano.kanoapiclientsdk.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

public class SignUtil {

    /**
     * 生成签名
     * @param body
     * @param sercetKey
     * @return
     */
    public static String genSign(String body,String sercetKey){
        // 使用SHA256算法的Digester
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + sercetKey;
        return md5.digestHex(content);
    }
}
