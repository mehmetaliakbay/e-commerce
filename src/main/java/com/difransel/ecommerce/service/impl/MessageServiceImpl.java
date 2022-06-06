package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.Message;
import com.difransel.ecommerce.repository.MessageRepository;
import com.difransel.ecommerce.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Message}.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(Message message) {
        log.debug("Request to save Message : {}", message);
        return messageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        log.debug("Request to save Message : {}", message);
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> partialUpdate(Message message) {
        log.debug("Request to partially update Message : {}", message);

        return messageRepository
            .findById(message.getId())
            .map(existingMessage -> {
                if (message.getName() != null) {
                    existingMessage.setName(message.getName());
                }
                if (message.getEmail() != null) {
                    existingMessage.setEmail(message.getEmail());
                }
                if (message.getPhone() != null) {
                    existingMessage.setPhone(message.getPhone());
                }
                if (message.getSubject() != null) {
                    existingMessage.setSubject(message.getSubject());
                }
                if (message.getMessage() != null) {
                    existingMessage.setMessage(message.getMessage());
                }
                if (message.getIp() != null) {
                    existingMessage.setIp(message.getIp());
                }
                if (message.getStatus() != null) {
                    existingMessage.setStatus(message.getStatus());
                }
                if (message.getCreatedAt() != null) {
                    existingMessage.setCreatedAt(message.getCreatedAt());
                }
                if (message.getUpdatedAt() != null) {
                    existingMessage.setUpdatedAt(message.getUpdatedAt());
                }

                return existingMessage;
            })
            .map(messageRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Message> findAll(Pageable pageable) {
        log.debug("Request to get all Messages");
        return messageRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Message> findOne(Long id) {
        log.debug("Request to get Message : {}", id);
        return messageRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Message : {}", id);
        messageRepository.deleteById(id);
    }
}
