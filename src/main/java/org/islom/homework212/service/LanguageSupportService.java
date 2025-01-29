package org.islom.homework212.service;

import org.islom.homework212.entity.Helps;
import org.islom.homework212.entity.LanguageSupport;
import org.islom.homework212.entity.MissCodeContent;
import org.islom.homework212.entity.MissCodePratice;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.LanguageSupportDto;
import org.islom.homework212.payload.MissCodeContentDto;
import org.islom.homework212.repository.HelpsRepo;
import org.islom.homework212.repository.LanguageSupportRepo;
import org.islom.homework212.repository.MissCodePracticeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageSupportService {

    LanguageSupportRepo repo;

    HelpsRepo helpsRepo;

    public List<LanguageSupport> findAll() {
        return repo.findAll();
    }

    public LanguageSupport findById(int id) {
        Optional<LanguageSupport> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(LanguageSupportDto dto) {
        Optional<Helps> helpsById = helpsRepo.findById(dto.getHelpsId());

        if (helpsById.isPresent()) {
            Helps pratice = helpsById.get();
           LanguageSupport update = new LanguageSupport();
            update.setId(dto.getId());
            update.setHelps(pratice);
            update.setContent(dto.getContent());
            update.setTitle(dto.getTitle());
            repo.save(update);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(LanguageSupportDto dto, int id) {
        Optional<LanguageSupport> byId = repo.findById(id);
        Optional<Helps> helpsById = helpsRepo.findById(dto.getHelpsId());
        if (byId.isPresent() && helpsById.isPresent()) {
            LanguageSupport update = byId.get();
            update.setHelps(helpsById.get());
            update.setContent(dto.getContent());
            update.setTitle(dto.getTitle());
            repo.save(update);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<LanguageSupport> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
