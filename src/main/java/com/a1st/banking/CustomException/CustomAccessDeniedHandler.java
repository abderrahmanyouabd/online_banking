package com.a1st.banking.CustomException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: Abderrahman Youabd aka: A1ST
 */

//@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // Customize the response for unauthorized users, e.g., set response status code and message
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/text");
        response.getWriter().write("Not allowed!");
    }


}
