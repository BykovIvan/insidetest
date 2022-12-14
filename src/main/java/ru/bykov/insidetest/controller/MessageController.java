package ru.bykov.insidetest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bykov.insidetest.model.dto.MessageDto;
import ru.bykov.insidetest.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<MessageDto> create(@Valid @RequestBody MessageDto messageDto) {
        log.info("Получен запрос к эндпоинту /message. Метод POST");
        return new ResponseEntity<>(messageService.createByUser(messageDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<MessageDto> getMessages(@Valid @RequestBody MessageDto messageDto) {
        log.info("Получен запрос к эндпоинту /message. Метод GET");
        return messageService.getMessagesByUser(messageDto);
    }
}
