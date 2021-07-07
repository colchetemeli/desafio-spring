package com.mercadolivre.desafio_spring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_spring.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository implements IPostRepository{

    private static final File FILE = new File("src/main/resources/posts.json");
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void persistPost(Post post) {
        try {
            List<Post> posts = this.getList();
            posts.add(post);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE)));
            mapper.writeValue(out, posts);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> fetchPostsByUser(int userId) {
        List<Post> postsByUser = new ArrayList<>();
        try {
            List<Post> posts = this.getList();
            for (Post post:posts
                 ) {
                if(post.getUserId() == userId){
                    postsByUser.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postsByUser;
    }

    @Override
    public int countPromoByUser(int userId) {
        return fetchPromosByUser(userId).size();
    }

    @Override
    public List<Post> fetchPromosByUser(int userId) {
        List<Post> promosByUser = new ArrayList<>();
        try {
            List<Post> postsByUser = this.fetchPostsByUser(userId);
            for (Post post:postsByUser
            ) {
                if(post.isHasPromo()){
                    promosByUser.add(post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promosByUser;
    }

    @Override
    public List<Post> fetchPromoByUser(int userId) {
        return null;
    }

    private List<Post> getList(){
        List<Post> posts = new ArrayList<>();
        try {
            FileInputStream is = new FileInputStream(FILE);
            TypeReference<List<Post>> typeReference = new TypeReference<>() {};
            posts = mapper.readValue(is, typeReference);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
