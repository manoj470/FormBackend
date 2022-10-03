package com.clover.form.service;

import com.clover.form.miscellaneous.JwtUtils;
import com.clover.form.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    LoginDetailsServiceImpl loginDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("inside do filter internal...........");

            String jwt = parseJwt(request);
            System.out.println("token:  "+jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                System.out.println("jwt token is valid........");
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                System.out.println("username: "+username);
                UserDetails userDetails = loginDetailsService.loadUserByUsername(username);
                System.out.println("UserDetails: "+userDetails);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception exception){
            System.out.println("Error in do filter: "+exception);
        }
        filterChain.doFilter(request, response);
    }
    private String parseJwt(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }
}
