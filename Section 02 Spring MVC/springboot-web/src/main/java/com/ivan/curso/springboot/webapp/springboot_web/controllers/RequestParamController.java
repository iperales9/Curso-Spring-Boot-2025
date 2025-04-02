package com.ivan.curso.springboot.webapp.springboot_web.controllers;

import com.ivan.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "hola que tal") String message) {

        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;

    }

    @GetMapping("/bar")
    public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {

        ParamDto params = new ParamDto();
        params.setMessage(text);
        params.setCode(code);
        return params;

    }

    @GetMapping("/request")
    public ParamDto request(HttpServletRequest request) {

        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(request.getParameter("text"));
        paramDto.setCode(request.getParameter("code") != null ? Integer.parseInt(request.getParameter("code")) : 0);
        return paramDto;

    }


}
