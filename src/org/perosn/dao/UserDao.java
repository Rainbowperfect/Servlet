package org.perosn.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.perosn.pojo.User;
import org.perosn.utils.C3P0Utils;

import java.sql.SQLException;

/**
 * @author RainbowPerferct/zero
 * @version v1.0
 * @create 2018/9/20/22:02
 */

public class UserDao {
    public User findUser(String username,String password){

        //1、创建查询对象
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());

        //创建查询条件
        String sql="select * from login where username=? and password=?";

        //查询用户
        User user=new User();
        if (user==null){
            return null;
        }
        try {
             user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }
}
