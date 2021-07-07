package com.mercadolivre.desafio_spring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    private static final File FILE = new File("src/main/resources/repository/users.json");

    @Override
    public User fetchById(int userId) {

        try {
            for (User user : this.getUsers() ) {
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
        List<User> users = getUsers();

        if (users.removeIf(user1 ->  user1.getId() == userId)) {

            users.add(user);

            try {

                PrintWriter buffer = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));

                buffer.flush();

                mapper.writeValue(buffer, users);

                buffer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return user;
    }


    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
            users = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
