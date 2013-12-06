package shop.Bestellverwaltung.service;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author <a href="mailto:koju1020@HS-Karlsruhe.de">Julian Kohlhaas</a>  
 */

@Qualifier
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface NeueBestellung {

}
