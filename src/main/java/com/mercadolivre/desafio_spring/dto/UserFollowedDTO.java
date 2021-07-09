package com.mercadolivre.desafio_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserFollowedDTO extends UserDTO{
    private List<UserDTO> followed = new ArrayList<>();

    public UserFollowedDTO(int id, String name, List<UserDTO> followed) {
        super(id, name);
        this.followed = followed;
    }
}
