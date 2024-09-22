package vn.loh.dao.impl;

import vn.loh.configs.DBConnectMySQL;
import vn.loh.dao.IUserDao;
import vn.loh.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";

        List<UserModel> users = new ArrayList<>();
        try {
            conn = getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setImage(rs.getString("image"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public UserModel findById(int id) {
        return null;
    }

    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";

        try {
            conn = getDatabaseConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next())
            {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setImage(rs.getString("image"));
                user.setRoleid(rs.getInt("roleid"));
                user.setCreatedate(rs.getDate("createdate"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void insert(UserModel userModel) {

    }

    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImpl();
        System.out.println(userDao.findByUsername("tuantp2004"));
    }
}
