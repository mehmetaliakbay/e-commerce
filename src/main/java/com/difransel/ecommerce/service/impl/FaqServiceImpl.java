package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.Faq;
import com.difransel.ecommerce.repository.FaqRepository;
import com.difransel.ecommerce.service.FaqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Faq}.
 */
@Service
@Transactional
public class FaqServiceImpl implements FaqService {

    private final Logger log = LoggerFactory.getLogger(FaqServiceImpl.class);

    private final FaqRepository faqRepository;

    public FaqServiceImpl(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public Faq save(Faq faq) {
        log.debug("Request to save Faq : {}", faq);
        return faqRepository.save(faq);
    }

    @Override
    public Faq update(Faq faq) {
        log.debug("Request to save Faq : {}", faq);
        return faqRepository.save(faq);
    }

    @Override
    public Optional<Faq> partialUpdate(Faq faq) {
        log.debug("Request to partially update Faq : {}", faq);

        return faqRepository
            .findById(faq.getId())
            .map(existingFaq -> {
                if (faq.getQuestion() != null) {
                    existingFaq.setQuestion(faq.getQuestion());
                }
                if (faq.getAnswer() != null) {
                    existingFaq.setAnswer(faq.getAnswer());
                }
                if (faq.getStatus() != null) {
                    existingFaq.setStatus(faq.getStatus());
                }
                if (faq.getCreatedAt() != null) {
                    existingFaq.setCreatedAt(faq.getCreatedAt());
                }
                if (faq.getUpdatedAt() != null) {
                    existingFaq.setUpdatedAt(faq.getUpdatedAt());
                }

                return existingFaq;
            })
            .map(faqRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Faq> findAll() {
        log.debug("Request to get all Faqs");
        return faqRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Faq> findOne(Long id) {
        log.debug("Request to get Faq : {}", id);
        return faqRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Faq : {}", id);
        faqRepository.deleteById(id);
    }
}
