package com.jinow.auth.config;

import com.jinow.auth.annotation.IntegrationTest;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.BeforeEach;

class ApplicationConfigTest {
    StandardPBEStringEncryptor pbeEnc;

    @BeforeEach
    public void init() {
        pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("jinowjasyptpassword");
        pbeEnc.setStringOutputType("base64");
    }

    @IntegrationTest
    public void jasypt_encrypt_test() {
        String secretKey = pbeEnc.encrypt("jinowHmacSecretKey");
        System.out.println(secretKey);
    }

    @IntegrationTest
    public void jasypt_decrypt_test() {
        String enc = "zDxIxPJx949pUw4r0OZDURSYqfn9EwsS793FU3nBFmBeh8Mc94jS9MDN3p6ZdYls";
        System.out.println(pbeEnc.decrypt(enc));
    }
}