package com.example.demosecucume.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsakeysConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
