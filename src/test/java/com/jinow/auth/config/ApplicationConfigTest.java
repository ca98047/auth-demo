package com.jinow.auth.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationConfigTest {
    StandardPBEStringEncryptor pbeEnc;

    @BeforeEach
    public void init() {
        pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword("jinowjasyptpassword");
        pbeEnc.setStringOutputType("base64");
    }

    @Test
    public void jasypt_encrypt_test() {
        String secretKey = pbeEnc.encrypt("jinowHmacSecretKey");
        System.out.println(secretKey);
    }

    @Test
    public void jasypt_decrypt_test() {
        //String enc = "DM8U19BZhT0saKzQfuA4aWgSFuVXDUh6Nh7cAAFDYhKYbNjsW//9znZTHVtcuYBQ4vN3rkU/iV/slHtXDcRatMX9ugBEAx7dCjHE1OVEtGegl3URkHWjitjQTxTttPD5qWZY/lMniP8TopaV6zklFe82M5mA53UaXiUvUHHZX+jF0v4vasRznbfHD3X0zQRWELx6bf/sYofNv1uUxPeHvP01kPogQaTDkcnd/Fb7Q4d4mKSZB11ifp5ECGc54FFRiv0h2z8ftq+EZcSH0LAZw3jh1sh15MZK1zh1i98SLB8yiu8oU789KKRSENSTWdHM3UGU6yCU3kqTgzgAdn7Oes4C4U3HnonH9UOfY8c8cBL+ZRrBn//p4HhUf65ebq8TiOBZJDtV6erl3eChTRtIYtCQGRVvYcuYoDIQMe1cXfnmahqQAUmz3Wc+y13n9hM57uxF37C1YJwcqGSE2Jbrz/227M1u4AGXIHOEoPmRG7MgKZTxRDQlcFOHe8Y4Q8HXOANFRx1ZDQGgaq6LkpaizPaATmLIa4MgcRqeZ4BJh5QdaP8xJV96DeXBZRllbvMvQok4lMYV49ONbj1wwB8Os3+iLt5UxBperIiBb/kUj3WVxAerxkw8pGHjbcGfZ2WSiM30cFuYD+lwGLLg06sOi1NzH3B7YQcdaltt/QrPVcREXA5qIKz4BRn6VJF2f4iBvxp1Nl8MA5lAhvL7DW6bJTueOGc+WnyrJAfsqaq+zRP69l94DSTdvGKzdUDpKL3mE07uJ9tFrF+HHWsIeCVaBxVz71QFbSRc3D9bSL8Vvluencp46QEHBp9d9qmKlvnH5aaaRLg94hXcIYlcdqs2n5lHDm2c1zBRuih5SyAlUNpU7embppZmuwk/pSHRHRA6U/PyOSgKgvBdYsGDJWhCXhz+llbPVPerRpxxuwnzsR/Ee9hmq0d+C2btXtWrAz4KwGbK/JcveXNEACVAIXpofyxIsUBygp41UflHwfxKO5GMNEdC5Ha/S4mfAPEF6OK142uN0KJVEtG8VWgjoThnSA5lIpGqfAuA3N9YGr8anqPZdvtj/XlgSk84u8lbJ0k1MBzw5dAWA5rIfGBRXXIj8lkhPzHHpzJt";
        String enc = "XOYqhwCwoykbWNjTl8DkmGw9ySBoSzrURl+fapuF7STeeRS3GySFCg==";
        //String enc = "WHoFMD7+uiTYn83i6+GA9hjSyC8tCs2bdpu9y8TyPei72bc0OeyE3zdox/VZqEEu";
        System.out.println(pbeEnc.decrypt(enc));

    }
}