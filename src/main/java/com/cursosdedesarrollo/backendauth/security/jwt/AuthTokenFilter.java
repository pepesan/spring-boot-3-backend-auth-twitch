package com.cursosdedesarrollo.backendauth.security.jwt;

import com.cursosdedesarrollo.backendauth.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.RequestContextFilter;
import java.io.IOException;
import java.util.Collection;

public class AuthTokenFilter extends RequestContextFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      logger.info("Peticion {}", request);
      String jwt = parseJwt(request);
      logger.info("JWT {}", jwt);
      Boolean validate = jwtUtils.validateJwtToken(jwt);
      logger.info("ValidateToken {}", validate);
      if (jwt != null && validate) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        logger.info("username {}", username);
        UserDetails userDetails = userService.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
        logger.info("Authorities: {}", authorities);
        UsernamePasswordAuthenticationToken authentication = 
            new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities
        );
        logger.info("Authentication: {}", authentication);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");

    // authorizationHeader contendrá el valor del encabezado "Authorization" de la solicitud
    // Puedes usarlo para obtener el token JWT y realizar las validaciones necesarias.
    String jwtToken = "";
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
       jwtToken = authorizationHeader.substring(7);
    }
    return jwtToken;
  }
}
