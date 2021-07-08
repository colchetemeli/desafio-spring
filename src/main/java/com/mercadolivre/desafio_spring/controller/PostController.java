package com.mercadolivre.desafio_spring.controller;

import com.mercadolivre.desafio_spring.dto.*;
import com.mercadolivre.desafio_spring.service.IPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController implements IPostController{

    private final IPostsService postsService;

    @Autowired
    public PostController(IPostsService postsService) {
        this.postsService = postsService;
    }

    //US 0005
    @Override
    @PostMapping("/newpost")
    public void createPost(@RequestBody PostDTO post) {
        postsService.createPost(post);
    }

    //US 0006
    @Override
    @GetMapping("/followed/{userId}/list")
    public FollowedPostsDTO getFollowedPosts(@PathVariable int userId, @RequestParam(defaultValue = "") String order) {
        return postsService.getFollowedPosts(userId, order);
    }

    //US 0010
    @Override
    @PostMapping("/newpromopost")
    public void createPromoPost(@RequestBody PromoPostDTO post) {
        postsService.createPromoPost(post);
    }

    //US 0011
    @Override
    @GetMapping("/{userId}/countPromo")
    public UserPromosCountedDTO countPromoByUser(@PathVariable int userId) {
        return postsService.countPromoByUser(userId);
    }

    //US 0012
    @Override
    @GetMapping("/{userId}/list")
    public UserPromosDTO getPromosByUser(@PathVariable int userId) {
        return postsService.getPromosByUser(userId);
    }
}
