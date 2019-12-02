package com.delicacy.grape.oauth.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yutao
 * @create 2019-10-24 14:35
 **/
@RestController
@RequestMapping("user")
public class UserRest {

    @GetMapping("get")
    @PreAuthorize("hasRole('ADMIN')")
    public Object get() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("get1")
    @PreAuthorize("hasRole('USER')")
    public Object get1() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
