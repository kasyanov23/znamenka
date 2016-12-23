package ru.click.crm.controller;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.click.core.model.Trainer;
import ru.click.core.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Глобальный обработчик ошибок.
 * Служит для маппинга ошибок и отдаче их клиенту в стандартизированном виде
 * <p>
 * Создан 10.06.2016
 *
 * @author Евгений Уткин (Eugene Utkin)
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {

    @ModelAttribute
    public Long extractTrainerIdIfExists(Authentication auth) {
        if (auth == null) return null;
        User user = (User) auth.getPrincipal();
        Trainer trainer = user.getTrainer();
        if (trainer != null) {
            return trainer.getId();
        }
        return null;
    }


    /**
     * Обработчик ошибок
     *
     * @param req запрос
     * @param e   обрабатываемая ошибка
     * @return представление ошибки для клиента
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Exception defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        return e;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle404(NoHandlerFoundException ex, HttpServletRequest req) {
        return new ModelAndView("error/404");
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public ModelAndView handle500(ConversionNotSupportedException ex, HttpServletRequest req) {
        return new ModelAndView("error/500");
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handle401(AccessDeniedException ex, HttpServletRequest req) {
        return new ModelAndView("error/401");
    }

}
