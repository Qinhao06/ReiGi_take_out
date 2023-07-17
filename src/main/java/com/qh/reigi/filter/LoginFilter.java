package com.qh.reigi.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.reigi.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest  request = (HttpServletRequest) servletRequest;
        HttpServletResponse  response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURI());
        String uri = request.getRequestURI();

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
        };
        if(check_url(urls, uri)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();

        if(session.getAttribute("employee") != null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(R.error("NOTLOGIN"));
        response.getWriter().write(s);


    }


    public boolean check_url(String[] urls, String uri) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, uri)) {
                return true;
            }
        }
        return false;
    }
}
