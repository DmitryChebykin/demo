package com.example.demo.service;

import com.example.demo.api.rest.controller.ApplicationStateService;
import com.example.demo.controller.IdleController;
import com.example.demo.event.WSMessageEvent;
import com.example.demo.service.navigation.NavigationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class WSMessageEventListener {
    private final NavigationService navigationService;

    private final AtomicBoolean isHandleEventEnabled = new AtomicBoolean();

    private final MessageBasketService messageBasketService;

    private final ApplicationStateService applicationStateService;

    @SneakyThrows
    @EventListener
    public void print(WSMessageEvent wsMessageEvent) {
        String payload = wsMessageEvent.getMessage().getPayload().toString();
        if (applicationStateService.getActiveController().get() instanceof IdleController) {
            navigationService.showCustomerViewController();
            ObjectMapper mapper = new ObjectMapper();
            List<String> stringList = mapper.readValue(payload, new TypeReference<List<String>>() {});
            messageBasketService.setStringList(stringList);
        }
    }

    @PostConstruct
    public void setIsHandleEventEnabled() {
        isHandleEventEnabled.set(true);
    }

    public void setIsHandleEventDisabled() {
        isHandleEventEnabled.set(false);
    }
}


