
package com.delicacy.grape.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EchoImportBeanDefinitionRegistrar.class,EchoImportSelector.class})
public @interface EnableEcho {
    String[] packages() default "";
}