package dev.danvega.service;

import dev.danvega.domain.User;
import dev.danvega.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve all users
    public Iterable<User> list() {
        return userRepository.findAll();
    }

    // Save a user
    public User save(User user) {
        return userRepository.save(user);
    }

    // Save a list of users
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }

    // Retrieve a list of users sorted by a specific field
    public List<User> recordsWithSorting(String field) {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Retrieve a paginated list of users sorted by a specific field
    public List<User> recordsWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<User> userPage = userRepository.findAll(
                PageRequest.of(offset, pageSize, Sort.by(field)));
        return userPage.getContent();
    }
}
