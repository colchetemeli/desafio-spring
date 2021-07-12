package com.mercadolivre.desafio_spring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.exception.ConflictException;
import com.mercadolivre.desafio_spring.exception.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PostRepository implements IPostRepository{

    private static final String MSG_POST_IN_USE = "This post_id already has been used";
    private static final String MSG_CREATE_ERROR = "Error to create post";
    private static final String MSG_LOADING_LIST_ERROR = "Error to get post list";

    private static final File FILE = new File("src/main/resources/repository/posts.json");
    private final ObjectMapper mapper;

    @Autowired
    public PostRepository(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void persistPost(Post post) {
        List<Post> posts = this.getList();
        if ( posts.stream().anyMatch(p -> Objects.equals(p.getId(),post.getId())) ){
            throw new ConflictException(MSG_POST_IN_USE);
        }
        posts.add(post);
        try {
            mapper.writeValue(FILE, posts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(MSG_CREATE_ERROR);
        }
    }

    @Override
    public List<Post> fetchPostsByUser(int userId) {
        return this.getList().stream()
                    .filter(p -> Objects.equals( p.getUserId(), userId ))
                    .collect(Collectors.toList());
    }

    @Override
    public int countPromoByUser(int userId) {
        return fetchPromosByUser(userId).size();
    }

    @Override
    public List<Post> fetchPromosByUser(int userId) {
        return this.fetchPostsByUser(userId).stream()
                .filter(Post::isHasPromo)
                .collect(Collectors.toList());
    }

    private List<Post> getList(){
        List<Post> posts = new ArrayList<>();
        try {
            posts = mapper.readValue(FILE, new TypeReference<>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(MSG_LOADING_LIST_ERROR);
        }
        return posts;
    }
}
