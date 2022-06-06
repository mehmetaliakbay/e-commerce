package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.domain.ShoppingCard;
import com.difransel.ecommerce.repository.ShoppingCardRepository;
import com.difransel.ecommerce.service.ShoppingCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ShoppingCard}.
 */
@Service
@Transactional
public class ShoppingCardServiceImpl implements ShoppingCardService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCardServiceImpl.class);

    private final ShoppingCardRepository shoppingCardRepository;

    public ShoppingCardServiceImpl(ShoppingCardRepository shoppingCardRepository) {
        this.shoppingCardRepository = shoppingCardRepository;
    }

    @Override
    public ShoppingCard save(ShoppingCard shoppingCard) {
        log.debug("Request to save ShoppingCard : {}", shoppingCard);
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public ShoppingCard update(ShoppingCard shoppingCard) {
        log.debug("Request to save ShoppingCard : {}", shoppingCard);
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public Optional<ShoppingCard> partialUpdate(ShoppingCard shoppingCard) {
        log.debug("Request to partially update ShoppingCard : {}", shoppingCard);

        return shoppingCardRepository
            .findById(shoppingCard.getId())
            .map(existingShoppingCard -> {
                if (shoppingCard.getAmount() != null) {
                    existingShoppingCard.setAmount(shoppingCard.getAmount());
                }

                return existingShoppingCard;
            })
            .map(shoppingCardRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCard> findAll() {
        log.debug("Request to get all ShoppingCards");
        return shoppingCardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ShoppingCard> findOne(Long id) {
        log.debug("Request to get ShoppingCard : {}", id);
        return shoppingCardRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ShoppingCard : {}", id);
        shoppingCardRepository.deleteById(id);
    }
}
