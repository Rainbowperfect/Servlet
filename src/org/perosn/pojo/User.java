package org.perosn.pojo;

import java.io.Serializable;

/**
 * @author RainbowPerferct/zero
 * @version v1.0
 * 序列化：将对象转化为二进制存入磁盘
 * 反序列化：把二进制的转化为对象
 *
 */

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String nickname;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", nickname='" + nickname + '\'' + '}';
    }
}
