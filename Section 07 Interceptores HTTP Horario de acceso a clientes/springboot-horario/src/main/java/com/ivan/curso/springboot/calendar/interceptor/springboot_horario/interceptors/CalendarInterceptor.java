package com.ivan.curso.springboot.calendar.interceptor.springboot_horario.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;

    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(hour);

        if (hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("Bienvendios al horario de atencion a clientes");
            message.append(", atendemos desde las ");
            message.append(open);
            message.append("hrs.");
            message.append(" hasta las ");
            message.append(close);
            message.append("hrs.");
            message.append(" Gracias por su visita!");
            request.setAttribute("message", message.toString());
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        StringBuilder message = new StringBuilder("Cerrado, fuere del horario de atencion ");
        message.append("por favor visitenos desde las ");
        message.append(open);
        message.append(" y las ");
        message.append(close);
        message.append(" hrs. Gracias!");
        data.put("message", message.toString());
        data.put("date", new Date().toString());
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

    }

}
