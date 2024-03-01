package com.library.libraryuserservice.services;

import com.library.libraryuserservice.exceptions.NotFoundException;
import com.library.libraryuserservice.models.Role;
import com.library.libraryuserservice.models.User;
import com.library.libraryuserservice.repos.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getAllEntries() {
        return repository.findAll();
    }

    @Override
    public Role getEntryById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role by this "+id+" not found in database"));
    }

    @Override
    public Role create(Role obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).ifPresent(result -> repository.deleteById(id));
    }
}
