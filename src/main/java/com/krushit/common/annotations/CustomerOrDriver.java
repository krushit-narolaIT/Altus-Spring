package com.krushit.common.annotations;

import com.krushit.common.enums.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RoleAllowed({RoleType.ROLE_CUSTOMER, RoleType.ROLE_DRIVER})
public @interface CustomerOrDriver {}
