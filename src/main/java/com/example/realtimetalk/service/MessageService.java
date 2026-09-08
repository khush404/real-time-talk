package com.example.realtimetalk.service;

import com.example.realtimetalk.entity.Message;
import com.example.realtimetalk.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(String sender, String receiver, String content){

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getConversation(String user1, String user2){
        List<Message> sentMessages = messageRepository.findBySenderAndReceiver(user1, user2);
        List<Message> receivedMessages = messageRepository.findByReceiverAndSender(user1, user2);
        sentMessages.addAll(receivedMessages);
        sentMessages.sort((m1,m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()));

        return sentMessages;
    }
}
