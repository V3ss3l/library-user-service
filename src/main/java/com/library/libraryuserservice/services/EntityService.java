package com.library.libraryuserservice.services;

import com.library.libraryuserservice.exceptions.NotFoundException;
import com.library.libraryuserservice.models.User;

import java.util.List;

public interface EntityService<T> {

    public List<T> getAllEntries();

    public T getEntryById(Long id) throws NotFoundException;

    public T create(T obj);

    public void delete(Long id);
}
