package com.sail.pojo;

/**
 * @program: JavaDemo
 * @description: TODO
 * @author: sail
 * @create: 2020/4/28 22:27
 */

public class User {
    private Long id;

    private String username;

    private String nickname;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
