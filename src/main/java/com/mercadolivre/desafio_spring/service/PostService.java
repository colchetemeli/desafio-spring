package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.*;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostsService{

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(PostDTO post) {
        this.postRepository.persistPost(post.toEntity());
    }

    @Override
    public FollowedPostsDTO getFollowedPosts(int userId, String order) {
        return new FollowedPostsDTO(userId, getOrderedPostList(userId, order));
    }

    @Override
    public void createPromoPost(PromoPostDTO post) {

    }

    @Override
    public UserPromosCountedDTO countPromoByUser(int userId) {
        return null;
    }

    @Override
    public UserPromosDTO getPromosByUser(int userId) {
        return null;
    }

    public List<PostDTO> getOrderedPostList(int userId, String order) {
        List<Post> orderedPostList = this.postRepository.fetchPostsByUser(userId)
                .stream().sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());

        if(order.equals("date_desc")) {
            Collections.reverse(orderedPostList);
        }

        List<PostDTO> postDTOList = new ArrayList<>();

        orderedPostList.forEach(p -> {
            postDTOList.add(p.toPostDTO());
        });

        return postDTOList;
    }
}
