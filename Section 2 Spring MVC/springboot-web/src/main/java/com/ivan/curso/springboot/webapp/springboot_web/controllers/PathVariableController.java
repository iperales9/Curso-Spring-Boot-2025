package com.ivan.curso.springboot.webapp.springboot_web.controllers;


import com.ivan.curso.springboot.webapp.springboot_web.models.User;
import com.ivan.curso.springboot.webapp.springboot_web.models.dto.ParamDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.username}")
    private String username;

    @Value("${config.code}")
    private Integer code;

    @Value(("${config.listOfValues}"))
    private List<String> listOfValues;

    @Value("#{ '${config.listOfValues}'.split(',') }")
    private List<String> valuelist;

    @Value("#{ '${config.listOfValues}'.toUpperCase() }")
    private String valueString;

    @Value("#{ ${config.valuesMap} }")
    private Map<String, String> valuesMap;

    @Value("# {${config.valuesMap}.product} ")
    private String product;

    @Value("#{ ${config.valuesMap}.price }")
    private Long price;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {
        ParamDto param = new ParamDto();
        param.setMessage(message);

        return param;
    }


    @GetMapping("/mix/{message}/{code}")
    public ParamDto mix(@PathVariable String message, @PathVariable Integer code) {
        ParamDto param = new ParamDto();
        param.setMessage(message);
        param.setCode(code);

        return param;
    }

    // Es necesario enviar un objeto User en el cuerpo de la petición y tiene que tener un constructor vacio
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return user;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("message", message);
        response.put("message2", environment.getProperty("config.message"));
        response.put("code", code);
        response.put("code2", Integer.valueOf(Objects.requireNonNull(environment.getProperty("config.code"))));
        response.put("listOfValues", listOfValues);
        response.put("valuelist", valuelist);
        response.put("valueString", valueString);
        response.put("valuesMap", valuesMap);
        response.put("product", product);
        response.put("price", price);


        return response;
    }


}
