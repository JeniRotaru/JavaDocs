package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

/**
 * Created by user on 7/14/2016.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyController {

    String urlPath();

}
