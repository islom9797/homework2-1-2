package org.islom.homework212.service;

import org.islom.homework212.entity.User;
import org.islom.homework212.payload.ApiResponse;
import org.islom.homework212.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepo repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(int id) {
        Optional<User> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse save(User user) {

        User save = repo.save(user);

        return save == null ? new ApiResponse("xato", false) : new ApiResponse("saqlandi", true);
    }

    public ApiResponse update(User user, int id) {
        Optional<User> byId = repo.findById(id);
        if (byId.isPresent()) {
            User update = byId.get();
            update.setUsername(user.getUsername());
            update.setPassword(user.getPassword());
            update.setBirthday(user.getBirthday());
            repo.save(update);
            return new ApiResponse("saqlandi", true);
        }
        return new ApiResponse("xato", false);

    }

    public ApiResponse deleteById(int id) {
        Optional<User> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.deleteById(id);
            return new ApiResponse("o'chirildi", true);
        }
        return new ApiResponse("xato", false);
    }

}
