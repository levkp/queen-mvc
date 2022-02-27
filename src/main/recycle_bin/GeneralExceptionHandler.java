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
//public class GeneralExceptionHandler {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal server occurred")
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView error500Handler(HttpServletRequest request, Exception e) {
////
////        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
////            throw e;
//
//        log.error(e.getMessage());
//        e.printStackTrace();
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", request.getRequestURL());
//        mav.setViewName("error_500");
//
//        return mav;
//    }
//}
