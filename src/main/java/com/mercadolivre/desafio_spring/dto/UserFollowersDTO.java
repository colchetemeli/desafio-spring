package com.mercadolivre.desafio_spring.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserFollowersDTO extends UserDTO{
    private List<UserDTO> followers = new ArrayList<>();

    public UserFollowersDTO(int id, String name, List<UserDTO> followers) {
        super(id, name);
        this.followers = followers;
    }
}
