package com.example.demo.api.rest.controller;

import com.example.demo.service.MessageBasketService;
import com.example.demo.config.GuiState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/api")
public class MainController {
    private final ApplicationStateService applicationStateService;

    private final MessageBasketService messageBasketService;

    @GetMapping
     public ResponseEntity<Void> changeState(@RequestParam String state) throws Exception {
        applicationStateService.setGuiStateAtomicReference(GuiState.valueOf(state));
        return ResponseEntity.ok().build();
    }

    @GetMapping("basket")
    public ResponseEntity<List<String>> getBasket(){
        return  new ResponseEntity<>(messageBasketService.getStringList(), HttpStatus.OK);
    }
}
