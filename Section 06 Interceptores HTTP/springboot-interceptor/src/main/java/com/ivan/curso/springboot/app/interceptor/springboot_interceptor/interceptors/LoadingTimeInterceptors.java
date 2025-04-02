package com.ivan.curso.springboot.app.interceptor.springboot_interceptor.interceptors;


import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;*/
import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptors implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptors.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("LoadingTimeInterceptor: preHandle() -- Entrando en método: {}", ((HandlerMethod) handler).getMethod().getName());

        long startTime = System.currentTimeMillis();
        request.setAttribute("start", startTime);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

       /* Map<String, String> json = new HashMap<>();
        json.put("error", "no tienes acceso a esta pagina!!");
        json.put("date", new Date().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(objectMapper.writeValueAsString(json));

        return false;*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("start");
        long loadingTime = endTime - startTime;
        logger.info("Tiempo transcurido {} ms", loadingTime);
        logger.info("LoadingTimeInterceptor: postHandle() -- Saliendo en método: {}", ((HandlerMethod) handler).getMethod().getName());

    }

}
