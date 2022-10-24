package com.example.ej157crudsecurity.feign;

import com.example.ej157crudsecurity.dto.output.ProfesorOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "respuestaFeign", url="localhost:8081")
public interface Feign {

    @GetMapping("profesores/{id}")
    public ProfesorOutputDTO dameProfesor(@PathVariable String id);
}
