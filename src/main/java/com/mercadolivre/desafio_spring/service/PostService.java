package com.mercadolivre.desafio_spring.service;

import com.mercadolivre.desafio_spring.dto.*;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.entity.User;
import com.mercadolivre.desafio_spring.exceptions.CreatePostException;
import com.mercadolivre.desafio_spring.repository.PostRepository;
import com.mercadolivre.desafio_spring.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostsService {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public PostService(PostRepository postRepository, UsersRepository usersRepository) {
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void createPost(PostDTO postDto) {
        validateUser(postDto.getUserId());
        this.postRepository.persistPost(postDto.toEntity());

    }

    @Override
    public FollowedPostsDTO getFollowedPosts(int userId, String order) {
        return new FollowedPostsDTO(userId, getOrderedPostList(userId, order));
    }

    @Override
    public void createPromoPost(PromoPostDTO promoPostDto) {
        validateUser(promoPostDto.getUserId());
        this.postRepository.persistPost(promoPostDto.toEntity());
    }

    @Override
    public UserPromosCountedDTO countPromoByUser(int userId) {
        int quantityPromoPosts = this.postRepository.countPromoByUser(userId);
        User user = this.usersRepository.fetchById(userId);
        return new UserPromosCountedDTO(user.getId(), user.getName(), quantityPromoPosts);
    }

    @Override
    public UserPromosDTO getPromosByUser(int userId) {
        List<Post> listPosts = orderList(this.postRepository.fetchPromosByUser(userId), "date_desc");
        List<PromoPostDTO> promoPostDTOlist = listPosts.stream()
                .map(Post::toPromoPostDTO)
                .collect(Collectors.toList());

        User user = this.usersRepository.fetchById(userId);

        return new UserPromosDTO(user.getId(), user.getName(), promoPostDTOlist);
    }

    public List<PostDTO> getOrderedPostList(int userId, String order) {
        List<Post> orderedPostList = orderList(this.postRepository.fetchPostsByUser(userId), order);

        return orderedPostList.stream()
                .map(Post::toPostDTO)
                .collect(Collectors.toList());
    }

    private void validateUser(int userId) {
        if (Objects.isNull(usersRepository.fetchById(userId))) {
            throw new CreatePostException("Could not create a new post, this user does not exists!");
        }
    }

    private List<Post> orderList(List<Post> list, String order) {
        List<Post> orderedList = list
                .stream().sorted(Comparator.comparing(Post::getDate)).collect(Collectors.toList());

        if (order.equalsIgnoreCase("date_desc")) {
            Collections.reverse(orderedList);
        }

        return orderedList;
    }
}
