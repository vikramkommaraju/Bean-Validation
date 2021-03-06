package github.vikram.javaee.beanvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;


@Qualifier
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface USA {

}
