package org.islom.homework212.service;

import org.islom.homework212.entity.Problems;
import org.islom.homework212.entity.ProgrammingCategory;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.ProblemDto;
import org.islom.homework212.repository.ProblemsRepo;
import org.islom.homework212.repository.ProgrammingCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemsService {
    ProblemsRepo repo;


    ProgrammingCategoryRepo categoryRepo;


    public List<Problems> findAll() {
        return repo.findAll();
    }

    public Problems findById(int id) {
        Optional<Problems> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(ProblemDto problemDto) {
        Optional<ProgrammingCategory> categoryRepoById = categoryRepo.findById(problemDto.getCategoryId());

        if (categoryRepoById.isPresent()) {
            ProgrammingCategory category = categoryRepoById.get();
            Problems problem = new Problems();
            problem.setName(problem.getName());
            problem.setContent(problemDto.getContent());
            problem.setProgrammingCategory(category);
            repo.save(problem);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(ProblemDto problemDto, int id) {
        Optional<Problems> byId = repo.findById(id);
        Optional<ProgrammingCategory> categoryRepoById = categoryRepo.findById(problemDto.getCategoryId());
        if (byId.isPresent() && categoryRepoById.isPresent()) {
            ProgrammingCategory category = categoryRepoById.get();
            Problems problem = byId.get();
            problem.setName(problemDto.getName());
            problem.setContent(problemDto.getContent());
            problem.setProgrammingCategory(category);
            repo.save(problem);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<Problems> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
