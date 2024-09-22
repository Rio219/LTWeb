package vn.loh.services.impl;

import vn.loh.dao.IUserDao;
import vn.loh.dao.impl.UserDaoImpl;
import vn.loh.models.UserModel;
import vn.loh.services.IUserService;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.findByUsername(username);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            IUserService userService = new UserServiceImpl();
            System.out.println(userService.login("tuantp2004", "123456"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
