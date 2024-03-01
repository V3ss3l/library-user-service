package com.library.libraryuserservice.services;

import com.library.libraryuserservice.exceptions.NotFoundException;
import com.library.libraryuserservice.models.User;

import java.util.List;

public interface UserService extends EntityService<User>{
    User getUserByUsername(String username) throws NotFoundException;
}
