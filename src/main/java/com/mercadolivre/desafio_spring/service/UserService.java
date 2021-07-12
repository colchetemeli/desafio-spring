package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.FollowersCountedDTO;
import com.mercadolivre.desafio_spring.dto.UserDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowersDTO;
import com.mercadolivre.desafio_spring.entity.User;
import com.mercadolivre.desafio_spring.exception.FollowUnfollowException;
import com.mercadolivre.desafio_spring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private static final String MSG_USER_FOLLOWING_NOT_FOUND = "User following not found";
    private static final String MSG_USER_TO_FOLLOW_NOT_FOUND = "User to follow not found";
    private static final String MSG_USER_TO_UNFOLLOW_NOT_FOUND = "User to unfollow not found";
    private static final String MSG_USER_NOT_FOUND = "User not found";
    private static final String REVERSE_ORDERING = "name_desc";

    private final IUserRepository userRepository;


    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(int  userId, int userIdToFollow) {
            User userFollowing = getUserById(userId, MSG_USER_FOLLOWING_NOT_FOUND);
            User userToFollow = getUserById(userIdToFollow, MSG_USER_TO_FOLLOW_NOT_FOUND);
            validateFollow(userFollowing, userToFollow);

            userFollowing.getFollowed().add(userIdToFollow);
            userToFollow.getFollowers().add(userId);

            userRepository.update(userId, userFollowing);
            userRepository.update(userIdToFollow, userToFollow);
    }

    @Override
    public void unfollow(int userId, int userIdToUnfollow) {
        User userFollowing = getUserById(userId, MSG_USER_FOLLOWING_NOT_FOUND);
        User userToUnfollow = getUserById(userIdToUnfollow, MSG_USER_TO_UNFOLLOW_NOT_FOUND);

        validateUnfollow(userToUnfollow, userFollowing);

        userFollowing.getFollowed().remove(Integer.valueOf(userIdToUnfollow));
        userToUnfollow.getFollowers().remove(Integer.valueOf(userId));

        userRepository.update(userId, userFollowing);
        userRepository.update(userIdToUnfollow, userToUnfollow);

    }

    @Override
    public FollowersCountedDTO countFollowers(int userId) {
        User user = getUserById(userId, MSG_USER_NOT_FOUND);
        int numberOfFollowers = user.getFollowers().size();
        return new FollowersCountedDTO(userId, user.getName(), numberOfFollowers);
    }

    @Override
    public UserFollowersDTO getFollowers(int userId, String order) {
        User user = getUserById(userId, MSG_USER_NOT_FOUND);
        List<UserDTO> followers = getAllUsers(user.getFollowers());
        sortFollowers(followers, order);
        return new UserFollowersDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public UserFollowedDTO getFollowed(int userId, String order) {
        User user = getUserById(userId, MSG_USER_NOT_FOUND);
        List<UserDTO> followed = getAllUsers(user.getFollowed());
        sortFollowers(followed, order);
        return new UserFollowedDTO(user.getId(), user.getName(), followed);
    }

    private void sortFollowers(List<UserDTO> followersDTO, String order) {
        if (Objects.equals(order, REVERSE_ORDERING)) {
            followersDTO.sort(Comparator.comparing(UserDTO::getUserName).reversed());
        } else {
            followersDTO.sort(Comparator.comparing(UserDTO::getUserName));
        }
    }

    public List<UserDTO> getAllUsers(List<Integer> followersIds) {
        return followersIds.stream()
                .map(userRepository::fetchById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(follower -> new UserDTO(follower.getId(), follower.getName()))
                .collect(Collectors.toList());
    }

    private void validateFollow(User userFollowing, User userFollowed) {
        validateUsersNotEquals(userFollowing, userFollowed);
        validateIfUnfollows(userFollowing, userFollowed);
    }

    private void validateUnfollow(User userFollowing, User userFollowed) {
        validateIfFollows(userFollowing, userFollowed);
        validateUsersNotEquals(userFollowed, userFollowing);
    }

    private void validateUsersNotEquals(User user1, User user2) {
        if (Objects.equals(user1.getId(), user2.getId())) {
            throw new FollowUnfollowException("The users are the same.");
        }
    }

    private void validateIfUnfollows(User user, User user2) {
        if (user.getFollowed().contains(user2.getId())) {
            throw new FollowUnfollowException("The user is already following " + user2.getName());
        }
    }

    private void validateIfFollows(User userFollowing, User userFollowed) {
        if (!userFollowed.getFollowed().contains(userFollowing.getId())) {
            throw new FollowUnfollowException("The user does not follow " + userFollowed.getName());
        }
    }

    private User getUserById(int userId, String msgUserNotFound) {
        return userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException(msgUserNotFound));
    }
}
