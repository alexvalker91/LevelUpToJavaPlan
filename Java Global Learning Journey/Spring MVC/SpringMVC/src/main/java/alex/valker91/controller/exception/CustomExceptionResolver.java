package alex.valker91.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 . HandlerExceptionResolver (Интерфейс)
 Это «фундамент». Низкоуровневый механизм Spring MVC.

 Суть: Это интерфейс с одним методом resolveException.
 Как работает: Когда в приложении летит ошибка, Spring пробегает по списку всех бинов, реализующих этот интерфейс, и спрашивает: "Ты можешь это обработать?". Если класс говорит "Да" (возвращает ModelAndView), цепочка останавливается.
 Особенности: Ты работаешь с "сырыми" объектами (HttpServletRequest, HttpServletResponse). Ты обязан вернуть ModelAndView. Это "ручная коробка передач".
 2. @ExceptionHandler (Аннотация)
 Это «удобная надстройка».

 Суть: Аннотация, которую ты вешаешь на обычный метод.
 Как работает: Под капотом Spring использует специальный встроенный HandlerExceptionResolver, который умеет искать эти аннотации. Когда ошибка случается, он находит твой метод и просто вызывает его.
 Особенности: Ты не думаешь о сервлетах. Ты просто возвращаешь String, JSON (ResponseEntity) или что угодно. Это "автомат".
 **/
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        ModelAndView mav = new ModelAndView("error/error_page");

        mav.addObject("errorMessage", ex.getMessage());

        if (ex instanceof BadRequestException badRequestEx) {
            mav.addObject("details", badRequestEx.getErrors());
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return mav;
    }
}
