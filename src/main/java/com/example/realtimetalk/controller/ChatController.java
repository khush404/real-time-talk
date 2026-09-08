package com.example.realtimetalk.controller;

import com.example.realtimetalk.entity.Message;
import com.example.realtimetalk.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final MessageService messageService;

    public ChatController(MessageService messageService){
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message){
        return messageService.sendMessage(
                message.getSender(),
                message.getReceiver(),
                message.getContent()
        );
    }
}
