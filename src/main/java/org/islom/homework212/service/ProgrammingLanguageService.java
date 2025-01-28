package org.islom.homework212.service;

import org.islom.homework212.entity.ProgrammingLanguage;
import org.islom.homework212.entity.User;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.repository.ProgrammingLanguageRepo;
import org.islom.homework212.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {

    ProgrammingLanguageRepo repo;


    public List<ProgrammingLanguage> findAll() {
        return repo.findAll();
    }

    public  ProgrammingLanguage findById(int id) {
        Optional< ProgrammingLanguage> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(ProgrammingLanguage programmingLanguage) {

        ProgrammingLanguage save = repo.save(programmingLanguage);

        return save == null ? new ApiResponse("xato", false) : new ApiResponse("saqlandi", true);
    }

    public ApiResponse update(ProgrammingLanguage programmingLanguage, int id) {
        Optional<ProgrammingLanguage> byId = repo.findById( id);
        if (byId.isPresent()) {
            ProgrammingLanguage update = byId.get();
            update.setName(programmingLanguage.getName());
            repo.save(update);
            return new ApiResponse("saqlandi", true);
        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<ProgrammingLanguage> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
