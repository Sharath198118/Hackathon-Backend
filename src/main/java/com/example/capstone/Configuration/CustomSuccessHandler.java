package com.example.capstone.Configuration;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.example.capstone.Repository.UserRepository;


@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException
    {

        String redirectUrl = null;
        if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
        }
        redirectUrl = "http://localhost:8080/User/login";
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}

