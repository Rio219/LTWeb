package vn.loh.services;

import vn.loh.models.UserModel;

public interface IUserService {
    UserModel findByUsername(String username);
    UserModel login(String username, String password);
    void insert(UserModel userModel); // Add this line
}
