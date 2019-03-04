package com.example.househunters.Model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class UserDto {

    private String email;
    private String password;
    private String name;
    private String role;

    public UserDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(role, userDto.role);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(email, password, name, role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
