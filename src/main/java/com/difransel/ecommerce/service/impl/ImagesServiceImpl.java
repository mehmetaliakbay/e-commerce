package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.Images;
import com.difransel.ecommerce.repository.ImagesRepository;
import com.difransel.ecommerce.service.ImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Images}.
 */
@Service
@Transactional
public class ImagesServiceImpl implements ImagesService {

    private final Logger log = LoggerFactory.getLogger(ImagesServiceImpl.class);

    private final ImagesRepository imagesRepository;

    public ImagesServiceImpl(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public Images save(Images images) {
        log.debug("Request to save Images : {}", images);
        return imagesRepository.save(images);
    }

    @Override
    public Images update(Images images) {
        log.debug("Request to save Images : {}", images);
        return imagesRepository.save(images);
    }

    @Override
    public Optional<Images> partialUpdate(Images images) {
        log.debug("Request to partially update Images : {}", images);

        return imagesRepository
            .findById(images.getId())
            .map(existingImages -> {
                if (images.getTitle() != null) {
                    existingImages.setTitle(images.getTitle());
                }
                if (images.getImage() != null) {
                    existingImages.setImage(images.getImage());
                }

                return existingImages;
            })
            .map(imagesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Images> findAll() {
        log.debug("Request to get all Images");
        return imagesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Images> findOne(Long id) {
        log.debug("Request to get Images : {}", id);
        return imagesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Images : {}", id);
        imagesRepository.deleteById(id);
    }
}
