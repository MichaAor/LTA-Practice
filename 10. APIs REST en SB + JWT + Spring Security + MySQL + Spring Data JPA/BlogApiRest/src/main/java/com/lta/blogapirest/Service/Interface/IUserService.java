package com.lta.blogapirest.Service.Interface;

import com.lta.blogapirest.Model.User;

public interface IUserService {
    void Register(User user);
    String ExistsBy(String username);
}
