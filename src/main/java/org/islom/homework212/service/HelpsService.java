package org.islom.homework212.service;

import org.islom.homework212.entity.Helps;
import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.HelpsDto;
import org.islom.homework212.repository.HelpsRepo;
import org.islom.homework212.repository.ProgrammingLanguageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpsService {

    HelpsRepo repo;


    ProgrammingLanguageRepo languageRepo;

    public List<Helps> findAll() {
        return repo.findAll();
    }

    public Helps findById(int id) {
        Optional<Helps> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(HelpsDto dto) {
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(dto.getProgrammingLanguageId());

        if (languageRepoById.isPresent()) {
            ProgrammingLanguage language = languageRepoById.get();
            Helps helps = new Helps();
            helps.setProgrammingLanguage(language);
            helps.setTitle(dto.getTitle());
            repo.save(helps);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(HelpsDto dto, int id) {
        Optional<Helps> byId = repo.findById(id);
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(dto.getProgrammingLanguageId());
        if (byId.isPresent() && languageRepoById.isPresent()) {
            Helps update = byId.get();
            update.setProgrammingLanguage(languageRepoById.get());
            update.setTitle(dto.getTitle());
            repo.save(update);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<Helps> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
