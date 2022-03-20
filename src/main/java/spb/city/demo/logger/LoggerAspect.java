package spb.city.demo.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAspect {

    /**
     * Идет добавление аргументов в коллекцию List.
     * @param joinPoint
     * @return
     */
    private  List<String> getArgs (JoinPoint joinPoint) {
        List<String> args = new ArrayList<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            Object argValue = joinPoint.getArgs()[i];
            args.add("arg." + i + argValue);
        }
        return args;
    }

    /**
     * Заглушка, используются все методы из пакета spb.city.demo.rest.Contropller.VkladRestController.
     * (..)- принимает любые параметры.
     * * возвращаемое значение и области видимости неважны.
     */
    @Pointcut("execution(* spb.city.demo.controller.VkladRestController.*(..))")
    public void vkladRestControllerPointcut() {
    }

    /**
     * Сообщение показывающее используемые методы, аргументы и что они успешно начаты
     * @param joinPoint
     */
    @Before("vkladRestControllerPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method = " + methodName + " args = " + args + " started.");
    }

    /**
     * Сообщение показывающее используемые методы, аргументы и что они успешно закончены
     * @param joinPoint
     */
    @AfterReturning("vkladRestControllerPointcut()")
    public void aftherMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        System.out.println("Method = " + methodName + " args = " + args + " finish.");
    }

    /**
     * Если есть исключение то оно присваивается переменной ex и выводится пользователю
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value = "vkladRestControllerPointcut()", throwing = "ex")
    public void aftherExeption(JoinPoint joinPoint, Throwable ex){

        String methodName = joinPoint.getSignature().getName();
        List<String> args = getArgs(joinPoint);
        String message = String.format("ERROR!!! Failed to method = %s " +
                        "args = %s error = %s",
                         methodName, args, ex);
        System.out.println(message);
    }
}






