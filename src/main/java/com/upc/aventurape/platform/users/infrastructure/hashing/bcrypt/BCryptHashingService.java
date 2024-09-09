package com.upc.aventurape.platform.users.infrastructure.hashing.bcrypt;

import com.upc.aventurape.platform.users.aplication.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
