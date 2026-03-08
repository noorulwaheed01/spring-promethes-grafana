package com.amrut.prabhu.Prometheusintegration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtTokenEncoderDecoder {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String s = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Ik5PT1IgVUwgV0FIRUVEIiwidXNlci1yb2xlcyI6WyJQdWxsZXIgRGVsaXZlcnkvUGlja3VwIl0sImlzcyI6Imh0dHBzOi8vZ2VuZXNpcy1haS51cy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjhlNTVkN2ZmZjQ0NTBkODQwZjliMTI5IiwiYXVkIjoiaHR0cHM6Ly9hcGktdWF0Lndtcy5zdG1hdGVjaC5jb20iLCJpYXQiOjE3NzI5NzIzMjIsImV4cCI6MTc3MzA1ODcyMiwic2NvcGUiOiJvZmZsaW5lX2FjY2VzcyIsImd0eSI6WyJyZWZyZXNoX3Rva2VuIiwicGFzc3dvcmQiXSwiYXpwIjoiVUZrOEIxQkZBMTV4akd5Z0pnRG0wQXN4Y0tPWVlaUTYiLCJwZXJtaXNzaW9ucyI6W119.LJwuGa6VJ4_AC-GH0XhRX4a_TusbxVnHrudUUxNYqmo";


        String[] sections = s.split("\\.");

        String header = sections[0];
        String payload = sections[1];
        String signature = sections[2];

        String decodeHeader = new String(Base64.getUrlDecoder().decode(header));
        String decodePayload =new String(Base64.getUrlDecoder().decode(payload));

        System.out.println(decodeHeader);
        System.out.println(decodePayload);


        String encodeHeader = new String(Base64.getEncoder().withoutPadding().encode(decodeHeader.getBytes()));
        String encodePayload =new String(Base64.getEncoder().withoutPadding().encode(decodePayload.getBytes()));

        System.out.println(encodeHeader);
        System.out.println(encodePayload);

        String data = encodeHeader + "." + encodePayload;


        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey =
                new SecretKeySpec("s".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        mac.init(secretKey);

        byte[] signatureBytes = mac.doFinal(data.getBytes());

        String signatureGenreated = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(signatureBytes);


        System.out.println(signatureGenreated);

    }

}
