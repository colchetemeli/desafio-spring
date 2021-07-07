package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.*;

public interface IPostsService {

    void createPost(PostDTO post);
    FollowedPostsDTO getFollowedPosts(int userId, String order);
    void createPromoPost(PromoPostDTO post);
    UserPromosCountedDTO countPromoByUser(int userId);
    UserPromosDTO getPromosByUser(int userId);
    
}
