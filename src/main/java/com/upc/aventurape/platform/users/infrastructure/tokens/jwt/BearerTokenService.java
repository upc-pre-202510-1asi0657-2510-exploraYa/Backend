package com.upc.aventurape.platform.users.infrastructure.tokens.jwt;

import com.upc.aventurape.platform.users.aplication.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;


public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest request);
    String getGenerateToken(Authentication authentication);
}
