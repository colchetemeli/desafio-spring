package com.mercadolivre.desafio_spring.controller;

import com.mercadolivre.desafio_spring.dto.FollowersCountedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowedDTO;
import com.mercadolivre.desafio_spring.dto.UserFollowersDTO;
import com.mercadolivre.desafio_spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController implements IUserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //US 0001
    @Override
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable int userId, @PathVariable int userIdToFollow) {
        userService.follow(userId, userIdToFollow);
    }

    //US 0007
    @Override
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        userService.unfollow(userId, userIdToUnfollow);
    }

    //US 0002
    @Override
    @GetMapping("/{userId}/followers/count")
    public FollowersCountedDTO countFollowers(@PathVariable int userId) {
        return userService.countFollowers(userId);
    }

    //US 0003
    @Override
    @GetMapping("/{userId}/followers/list")
    public UserFollowersDTO getFollowers(@PathVariable int userId, @RequestParam(required = false) String order) {
        return userService.getFollowers(userId, order);
    }

    //US 0004
    @Override
    @GetMapping("/{userId}/followed/list")
    public UserFollowedDTO getFollowed(@PathVariable int userId, @RequestParam(required = false) String order) {
        return userService.getFollowed(userId, order);
    }
}
