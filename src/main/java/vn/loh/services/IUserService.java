package vn.loh.services;

import vn.loh.models.UserModel;

public interface IUserService {
    UserModel findByUsername(String username);
    UserModel login(String username, String password);
}
