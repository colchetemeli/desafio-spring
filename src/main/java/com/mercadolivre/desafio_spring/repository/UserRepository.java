package com.mercadolivre.desafio_spring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public User fetchById(int userId) {

        try {
            for (User user : this.getList() ) {
                if(user.getId() == userId){
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new User();
    }

    @Override
    public User update(int userId, User user) {
        return null;
    }


    private List<User> getList(){
        List<User> posts = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(new File("src/main/resources/users.json"));
            TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
            posts = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
