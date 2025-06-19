package com.krushit.common.annotations;

import com.krushit.common.enums.RoleType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAllowed {
    RoleType[] value();
}
