package com.example.bigsevaup.Model;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, POKUPATEL;

    @Override
    public String getAuthority() { return name(); }
}