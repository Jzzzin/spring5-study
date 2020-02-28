package ch10;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CountrySingerValidator.class)
@Documented
public @interface CheckCountrySinger {
    String message() default "컨트리 가수에는 반드시 성과 성별이 정의돼야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
