package ch5;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

// BeforeAdvice 이므로 MethodBeforeAdvice 를 구현해야 함
public class AuditAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Executing: " + method);
    }
}
