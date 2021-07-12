package com.mercadolivre.desafio_spring.repository;

import com.mercadolivre.desafio_spring.entity.Post;

import java.util.List;

public interface IPostRepository {

    void persistPost(Post post);
    List<Post> fetchPostsByUser(int userId);
    int countPromoByUser(int userId);
    List<Post> fetchPromosByUser(int userId);

}
