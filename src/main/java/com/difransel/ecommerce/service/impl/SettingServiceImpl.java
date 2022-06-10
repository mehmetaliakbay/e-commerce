package com.difransel.ecommerce.service.impl;

import com.difransel.ecommerce.model.Setting;
import com.difransel.ecommerce.repository.SettingRepository;
import com.difransel.ecommerce.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Setting}.
 */
@Service
@Transactional
public class SettingServiceImpl implements SettingService {

    private final Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);

    private final SettingRepository settingRepository;

    public SettingServiceImpl(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @Override
    public Setting save(Setting setting) {
        log.debug("Request to save Setting : {}", setting);
        return settingRepository.save(setting);
    }

    @Override
    public Setting update(Setting setting) {
        log.debug("Request to save Setting : {}", setting);
        return settingRepository.save(setting);
    }

    @Override
    public Optional<Setting> partialUpdate(Setting setting) {
        log.debug("Request to partially update Setting : {}", setting);

        return settingRepository
            .findById(setting.getId())
            .map(existingSetting -> {
                if (setting.getTitle() != null) {
                    existingSetting.setTitle(setting.getTitle());
                }
                if (setting.getKeywords() != null) {
                    existingSetting.setKeywords(setting.getKeywords());
                }
                if (setting.getDescription() != null) {
                    existingSetting.setDescription(setting.getDescription());
                }
                if (setting.getCompany() != null) {
                    existingSetting.setCompany(setting.getCompany());
                }
                if (setting.getAddress() != null) {
                    existingSetting.setAddress(setting.getAddress());
                }
                if (setting.getPhone() != null) {
                    existingSetting.setPhone(setting.getPhone());
                }
                if (setting.getFax() != null) {
                    existingSetting.setFax(setting.getFax());
                }
                if (setting.getEmail() != null) {
                    existingSetting.setEmail(setting.getEmail());
                }
                if (setting.getSmtpserver() != null) {
                    existingSetting.setSmtpserver(setting.getSmtpserver());
                }
                if (setting.getSmtpemail() != null) {
                    existingSetting.setSmtpemail(setting.getSmtpemail());
                }
                if (setting.getSmtppassword() != null) {
                    existingSetting.setSmtppassword(setting.getSmtppassword());
                }
                if (setting.getSmtpport() != null) {
                    existingSetting.setSmtpport(setting.getSmtpport());
                }
                if (setting.getFacebook() != null) {
                    existingSetting.setFacebook(setting.getFacebook());
                }
                if (setting.getInstagram() != null) {
                    existingSetting.setInstagram(setting.getInstagram());
                }
                if (setting.getTwitter() != null) {
                    existingSetting.setTwitter(setting.getTwitter());
                }
                if (setting.getAboutus() != null) {
                    existingSetting.setAboutus(setting.getAboutus());
                }
                if (setting.getContact() != null) {
                    existingSetting.setContact(setting.getContact());
                }
                if (setting.getReferences() != null) {
                    existingSetting.setReferences(setting.getReferences());
                }
                if (setting.getStatus() != null) {
                    existingSetting.setStatus(setting.getStatus());
                }
                if (setting.getCreatedAt() != null) {
                    existingSetting.setCreatedAt(setting.getCreatedAt());
                }
                if (setting.getUpdatedAt() != null) {
                    existingSetting.setUpdatedAt(setting.getUpdatedAt());
                }

                return existingSetting;
            })
            .map(settingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Setting> findAll() {
        log.debug("Request to get all Settings");
        return settingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Setting> findOne(Long id) {
        log.debug("Request to get Setting : {}", id);
        return settingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Setting : {}", id);
        settingRepository.deleteById(id);
    }
}
