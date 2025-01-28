package org.islom.homework212.service;

import org.islom.homework212.entity.ProgrammingCategory;
import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.ProgrammingCategoryDto;
import org.islom.homework212.repository.ProgrammingCategoryRepo;
import org.islom.homework212.repository.ProgrammingLanguageRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingCategoryService {
    ProgrammingCategoryRepo repo;

    ProgrammingLanguageRepo languageRepo;

    public List<ProgrammingCategory> findAll() {
        return repo.findAll();
    }

    public ProgrammingCategory findById(int id) {
        Optional<ProgrammingCategory> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(ProgrammingCategoryDto programmingCategoryDto) {
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(programmingCategoryDto.getProgrammingLanguageId());

        if (languageRepoById.isPresent()) {
            ProgrammingLanguage language = languageRepoById.get();
            ProgrammingCategory programmingCategory = new ProgrammingCategory();
            programmingCategory.setName(programmingCategoryDto.getName());
            programmingCategory.setProgrammingLanguage(language);
            repo.save(programmingCategory);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(ProgrammingCategoryDto programmingCategoryDto, int id) {
        Optional<ProgrammingCategory> byId = repo.findById(id);
        Optional<ProgrammingLanguage> languageRepoById = languageRepo.findById(programmingCategoryDto.getProgrammingLanguageId());
        if (byId.isPresent() && languageRepoById.isPresent()) {

            ProgrammingCategory update = byId.get();
            update.setName(programmingCategoryDto.getName());
            update.setProgrammingLanguage(languageRepoById.get());
            repo.save(update);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<ProgrammingCategory> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
