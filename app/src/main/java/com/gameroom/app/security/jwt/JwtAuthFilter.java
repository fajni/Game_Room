package com.gameroom.app.security.jwt;

import com.gameroom.app.security.model.User;
import com.gameroom.app.security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**

        OncePerRequestFilter - for every request, filter will activate.
        In the filter chain, this filter will be executed only once.

**/

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;


    // Behind the scenes, everything is happening with HttpServlets.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // When user send the request, inside the request you have JWT token
        // as Bearer token, so you need to cut the Bearer part from a token.
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);
            username = jwtService.extractAllClaims(token).getSubject();
        }

        // SecurityContextHolder.getContext().getAuthentication() -
        // - if the user is already authenticated don't do it again.
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // same as: User user = userService.findUserByUsername(username); just with using a bean
            User user = context.getBean(UserService.class).findUserByUsername(username);

            // if the jwt token is valid, do the next filter - (username-password filter)
            if(jwtService.validateToken(token, user)) {

                // since UsernamePasswordAuthenticationToken needs user authorities and you're using custom user/role
                List<GrantedAuthority> userAuthorities = List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()));

                UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        userAuthorities
                );
                // usernamePasswordToken (knows about user) needs to know about request details
                usernamePasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // adding the token to the chain
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordToken);
            }

        }

        filterChain.doFilter(request, response);

    }

}
