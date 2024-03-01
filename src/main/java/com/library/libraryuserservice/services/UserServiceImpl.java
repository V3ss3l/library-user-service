package com.library.libraryuserservice.services;

import com.library.libraryuserservice.exceptions.NotFoundException;
import com.library.libraryuserservice.models.User;
import com.library.libraryuserservice.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserByUsername(String username) throws NotFoundException{
        var result = repository.findByUsername(username);
        if (result != null) return result;
        else throw new NotFoundException("User by this |"+username+"| not found in database");
    }

    @Override
    public List<User> getAllEntries() {
        return repository.findAll();
    }

    @Override
    public User getEntryById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User by this "+id+" not found in database"));
    }

    @Override
    public User create(User obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(result -> repository.deleteById(id));
    }
}
