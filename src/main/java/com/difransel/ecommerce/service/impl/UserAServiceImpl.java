package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.UserA;
import com.difransel.ecommerce.repository.UserARepository;
import com.difransel.ecommerce.service.UserAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UserA}.
 */
@Service
@Transactional
public class UserAServiceImpl implements UserAService {

    private final Logger log = LoggerFactory.getLogger(UserAServiceImpl.class);

    private final UserARepository userARepository;

    public UserAServiceImpl(UserARepository userARepository) {
        this.userARepository = userARepository;
    }

    @Override
    public UserA save(UserA userA) {
        log.debug("Request to save UserA : {}", userA);
        return userARepository.save(userA);
    }

    @Override
    public UserA update(UserA userA) {
        log.debug("Request to save UserA : {}", userA);
        return userARepository.save(userA);
    }

    @Override
    public Optional<UserA> partialUpdate(UserA userA) {
        log.debug("Request to partially update UserA : {}", userA);

        return userARepository
            .findById(userA.getId())
            .map(existingUserA -> {
                if (userA.getName() != null) {
                    existingUserA.setName(userA.getName());
                }
                if (userA.getSurname() != null) {
                    existingUserA.setSurname(userA.getSurname());
                }
                if (userA.getEmail() != null) {
                    existingUserA.setEmail(userA.getEmail());
                }
                if (userA.getPassword() != null) {
                    existingUserA.setPassword(userA.getPassword());
                }
                if (userA.getRole() != null) {
                    existingUserA.setRole(userA.getRole());
                }
                if (userA.getStatus() != null) {
                    existingUserA.setStatus(userA.getStatus());
                }
                if (userA.getCreatedAt() != null) {
                    existingUserA.setCreatedAt(userA.getCreatedAt());
                }
                if (userA.getUpdatedAt() != null) {
                    existingUserA.setUpdatedAt(userA.getUpdatedAt());
                }

                return existingUserA;
            })
            .map(userARepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserA> findAll() {
        log.debug("Request to get all UserAS");
        return userARepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserA> findOne(Long id) {
        log.debug("Request to get UserA : {}", id);
        return userARepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserA : {}", id);
        userARepository.deleteById(id);
    }
}
