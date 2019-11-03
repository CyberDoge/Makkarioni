package com.example.Makkaroni.filters;

import com.example.Makkaroni.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    private final UserRepository userRepository;

    public AuthFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (!req.getServletPath().equalsIgnoreCase("/login")
                && !req.getServletPath().equalsIgnoreCase("/register")
                && !userRepository.existsByUsername(req.getHeader("username"))) {
            ((HttpServletResponse) servletResponse).setStatus(403);
            return;
        }

        userRepository.findUserByUsername(req.getHeader("username"))
                .ifPresent(user -> req.getSession().setAttribute("user", user));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
