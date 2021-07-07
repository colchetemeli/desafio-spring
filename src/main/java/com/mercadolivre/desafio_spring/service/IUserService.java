package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.FollowersCountedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowersDTO;

public interface IUserService {

    void follow(int userId, int userIdtoFollow);
    void unfollow(int userId, int userIdtoUnfollow);
    FollowersCountedDTO countFollowers(int userId);
    UserFollowersDTO getFollowers(int userId, String order);
    UserFollowedDTO getFollowed(int userId, String order);

}
