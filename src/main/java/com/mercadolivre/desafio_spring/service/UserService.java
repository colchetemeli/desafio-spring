package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.FollowersCountedDTO;
import com.mercadolivre.desafio_spring.dto.UserDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowersDTO;
import com.mercadolivre.desafio_spring.entity.User;
import com.mercadolivre.desafio_spring.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserService implements IUserService {

    final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(int userId, int userIdToFollow) {
        if (userId != userIdToFollow) {
            User userFollowing = userRepository.fetchById(userId);
            User userToFollow = userRepository.fetchById(userIdToFollow);

            userFollowing.getFollowed().add(userIdToFollow);
            userToFollow.getFollowers().add(userId);
        }
    }

    @Override
    public void unfollow(int userId, int userIdToUnfollow) {
        User userFollowing = userRepository.fetchById(userId);
        User userToUnfollow = userRepository.fetchById(userIdToUnfollow);
        userFollowing.getFollowed().remove(Integer.valueOf(userIdToUnfollow));
        userToUnfollow.getFollowers().remove(Integer.valueOf(userId));
    }

    @Override
    public FollowersCountedDTO countFollowers(int userId) {
        User user = userRepository.fetchById(userId);
        int numberOfFollowers = user.getFollowers().size();
        return new FollowersCountedDTO(userId, user.getName(), numberOfFollowers);
    }

    @Override
    public UserFollowersDTO getFollowers(int userId, String order) {
        User user = userRepository.fetchById(userId);
        List<UserDTO> followers = getAllSorted(user.getFollowers(), order);
        return new UserFollowersDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public UserFollowedDTO getFollowed(int userId, String order) {
        User user = userRepository.fetchById(userId);
        List<UserDTO> followed = getAllSorted(user.getFollowed(), order);
        return new UserFollowedDTO(user.getId(), user.getName(), followed);
    }

    private void sortFollowers(List<UserDTO> followersDTO, String order) {
        if (order.equals("name_desc")) {
            followersDTO.sort(Comparator.comparing(UserDTO::getUserName).reversed());
        } else {
            followersDTO.sort(Comparator.comparing(UserDTO::getUserName));
        }
    }

    private List<UserDTO> getAllSorted(List<Integer> followersIds, String order) {
        List<UserDTO> followersDTO = new ArrayList<>();
        for (Integer followerId : followersIds) {
            User follower = userRepository.fetchById(followerId);
            followersDTO.add(new UserDTO(follower.getId(), follower.getName()));
        }

        sortFollowers(followersDTO, order);
        return followersDTO;
    }
}
