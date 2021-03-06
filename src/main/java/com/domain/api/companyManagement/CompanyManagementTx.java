package com.domain.api.companyManagement;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional("companyManagementTransactionManager")
public @interface CompanyManagementTx {

    @AliasFor(annotation = Transactional.class, attribute = "readOnly")
    boolean readOnly() default false;

}
