//package prog21assignment.exceptions;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@ControllerAdvice
//public class PageNotFoundExceptionHandler {
//    private static final Logger log = LoggerFactory.getLogger(PageNotFoundExceptionHandler.class);
//
//    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Page not found")
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView error404Handler(HttpServletRequest request, Exception e) {
//        log.error(e.getMessage());
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", request.getRequestURL());
//        mav.setViewName("error_404");
//
//        return mav;
//    }
//}
