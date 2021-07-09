package com.mercadolivre.desafio_spring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FollowersCountedDTO  extends  UserDTO{
    @JsonProperty("followers_count")
    private int followersCount;

    public FollowersCountedDTO(int userId, String name, int numberOfFollowers) {
        super(userId, name);
        this.followersCount = numberOfFollowers;
    }
}
