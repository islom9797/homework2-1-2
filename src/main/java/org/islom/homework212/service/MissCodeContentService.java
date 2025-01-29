package org.islom.homework212.service;

import org.islom.homework212.entity.MissCodeContent;
import org.islom.homework212.entity.MissCodePratice;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.payload.MissCodeContentDto;
import org.islom.homework212.repository.MissCodeContentRepo;
import org.islom.homework212.repository.MissCodePracticeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissCodeContentService {

    MissCodeContentRepo repo;

    MissCodePracticeRepo practiceRepo;

    public List<MissCodeContent> findAll() {
        return repo.findAll();
    }

    public MissCodeContent findById(int id) {
        Optional<MissCodeContent> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(MissCodeContentDto dto) {
        Optional<MissCodePratice> practiceRepoById = practiceRepo.findById(dto.getMissCodePracticeId());

        if (practiceRepoById.isPresent()) {
            MissCodePratice pratice = practiceRepoById.get();
            MissCodeContent missCodeContent = new MissCodeContent();
            missCodeContent.setTitle(dto.getTitle());
            missCodeContent.setContent(dto.getContent());
            missCodeContent.setMissCodePratice(pratice);
            repo.save(missCodeContent);
            return new ApiResponse("saqlandi", true);
        }


        return new ApiResponse("xato", false);
    }

    public ApiResponse update(MissCodeContentDto dto, int id) {
        Optional<MissCodeContent> byId = repo.findById(id);
        Optional<MissCodePratice> practiceRepoById = practiceRepo.findById(dto.getMissCodePracticeId());
        if (byId.isPresent() && practiceRepoById.isPresent()) {
            MissCodeContent update = byId.get();
            update.setMissCodePratice(practiceRepoById.get());
            update.setContent(dto.getContent());
            update.setTitle(dto.getTitle());
            repo.save(update);
            return new ApiResponse("saqlandi", true);


        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<MissCodeContent> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }
}
