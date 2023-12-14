package com.crs.auth.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class JWTCustomConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        if (ObjectUtils.isEmpty(realmAccess)) {
            new ArrayList<>();
        }

        return ((List<String>) realmAccess.get("roles"))
                .stream()
                .map(roleName -> STR."ROLE_\{roleName}")
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
