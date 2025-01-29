package com.IT.SpringBootAngular.Entitys;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum Role implements GrantedAuthority{
    USER,
    ADMIN;

    private Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
