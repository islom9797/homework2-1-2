package org.islom.homework212.service;

import org.islom.homework212.entity.MissCodePratice;
import org.islom.homework212.entity.ProgrammingCategory;
import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.MissCodePracticeDto;
import org.islom.homework212.payload.ProgrammingCategoryDto;
import org.islom.homework212.repository.MissCodePracticeRepo;
import org.islom.homework212.repository.ProgrammingLanguageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissCodePracticeService {

    MissCodePracticeRepo repo;


    ProgrammingLanguageRepo languageRepo;

    public List<MissCodePratice> findAll() {
        return repo.findAll();
    }

    public MissCodePratice findById(int id) {
        Optional<MissCodePratice> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(MissCodePracticeDto missCodePracticeDto) {
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(missCodePracticeDto.getProgrammingLanguageId());

        if (languageRepoById.isPresent()) {
            ProgrammingLanguage language = languageRepoById.get();
            MissCodePratice missCodePratice = new MissCodePratice();
            missCodePratice.setProgrammingLanguage(language);
            missCodePratice.setName(missCodePracticeDto.getName());
            repo.save(missCodePratice);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(MissCodePracticeDto missCodePracticeDto, int id) {
        Optional<MissCodePratice> byId = repo.findById(id);
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(missCodePracticeDto.getProgrammingLanguageId());
        if (byId.isPresent() && languageRepoById.isPresent()) {

            MissCodePratice update = byId.get();
            update.setName(missCodePracticeDto.getName());
            update.setProgrammingLanguage(languageRepoById.get());
            repo.save(update);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<MissCodePratice> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
