package alex.valker91.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
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
