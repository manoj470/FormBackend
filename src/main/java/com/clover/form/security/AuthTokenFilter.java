package com.clover.form.security;

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
import java.util.Arrays;

@Service
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtils;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            System.out.println("inside filter process.... "+request);
            String jwt = parseJwt(request);
            System.out.println("JWT : "+jwt);
            if(jwt!=null){
                String username = jwtUtils.extractUserName(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(username,jwt,userDetails)
                        && SecurityContextHolder.getContext().getAuthentication()==null) {
                    System.out.println("jwt token valid.....");
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("SecurityContext is set....");
                }
            }

        }catch (Exception e){
            System.out.println("Error in doFilterInternal "+e+" ===> "+ Arrays.toString(e.getStackTrace()));
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }
}
