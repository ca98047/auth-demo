package com.jinow.auth.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class ApplicationConfigTest {

    @Test
    public void jasypt_encrypt_test() {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("jinowjasyptpassword");

        String secretKey = pbeEnc.encrypt("jinowHmacSecretKey");
        System.out.println(secretKey);

    }
}