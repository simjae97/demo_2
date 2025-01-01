package com.example.demo.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api")
    public class CookieController {

        @GetMapping("/set-cookie")
        @CrossOrigin(origins = "http://192.168.0.18:8080", allowCredentials = "true")  // CORS 허용 및 credentials 설정
        public ResponseEntity<String> setCookie(HttpServletResponse response) {
            System.out.println("쿠키세팅 호출");
            ResponseCookie cookie = ResponseCookie.from("sharedCookie", "value160112")
                    .path("/")
                    .httpOnly(true)
                    .sameSite("None") // 쿠키 공유를 위한 설정
                    .secure(true) // HTTPS 환경에서만 동작
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());
            return ResponseEntity.ok("Cookie set successfully!");
        }

        @GetMapping("/get-cookie")
        @CrossOrigin(origins = "http://192.168.0.18:8080", allowCredentials = "true")  // CORS 허용 및 credentials 설정
        public String getCookie(@CookieValue(name = "sharedCookie", defaultValue = "none") String cookieValue) {
            System.out.println("쿠키호출호출");
            System.out.println(cookieValue);
            return "Cookie Value: " + cookieValue;
        }
}