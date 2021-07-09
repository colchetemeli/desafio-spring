package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.FollowersCountedDTO;
import com.mercadolivre.desafio_spring.dto.UserDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowersDTO;
import com.mercadolivre.desafio_spring.entity.User;
import com.mercadolivre.desafio_spring.exception.ValidateException;
import com.mercadolivre.desafio_spring.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private static final String REVERSE_ORDERING = "name_desc";

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(int  userId, int userIdToFollow) {
            User userFollowing = userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
            User userToFollow = userRepository.fetchById(userIdToFollow).orElseThrow(() -> new NoSuchElementException("User not found"));
            validateFollow(userFollowing, userToFollow);

            userFollowing.getFollowed().add(userIdToFollow);
            userToFollow.getFollowers().add(userId);

            userRepository.update(userId, userFollowing);
            userRepository.update(userIdToFollow, userToFollow);
    }

    @Override
    public void unfollow(int userId, int userIdToUnfollow) {
        User userFollowing = userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        User userToUnfollow = userRepository.fetchById(userIdToUnfollow).orElseThrow(() -> new NoSuchElementException("User not found"));

        validateUnfollow(userToUnfollow, userFollowing);

        userFollowing.getFollowed().remove(Integer.valueOf(userIdToUnfollow));
        userToUnfollow.getFollowers().remove(Integer.valueOf(userId));

        userRepository.update(userId, userFollowing);
        userRepository.update(userIdToUnfollow, userToUnfollow);

    }

    @Override
    public FollowersCountedDTO countFollowers(int userId) {
        User user = userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        int numberOfFollowers = user.getFollowers().size();
        return new FollowersCountedDTO(userId, user.getName(), numberOfFollowers);
    }

    @Override
    public UserFollowersDTO getFollowers(int userId, String order) {
        User user = userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        List<UserDTO> followers = getAllUsers(user.getFollowers());
        sortFollowers(followers, order);
        return new UserFollowersDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public UserFollowedDTO getFollowed(int userId, String order) {
        User user = userRepository.fetchById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
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
                .filter(Objects::nonNull)
                .map(follower -> new UserDTO(follower.get().getId(), follower.get().getName()))
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
            throw new ValidateException("The users are the same.");
        }
    }

    private void validateIfUnfollows(User user, User user2) {
        if (user.getFollowed().contains(user2.getId())) {
            throw new ValidateException("The user is already following " + user2.getName());
        }
    }

    private void validateIfFollows(User userFollowing, User userFollowed) {
        if (!userFollowed.getFollowed().contains(userFollowing.getId())) {
            throw new ValidateException("The user does not follow " + userFollowed.getName());
        }
    }
}
