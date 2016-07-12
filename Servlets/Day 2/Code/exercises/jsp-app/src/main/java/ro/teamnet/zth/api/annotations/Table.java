package ro.teamnet.zth.api.annotations;

import ro.teamnet.zth.api.em.EntityUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {

    String name() default EntityUtils.EMPTY_STRING;
}