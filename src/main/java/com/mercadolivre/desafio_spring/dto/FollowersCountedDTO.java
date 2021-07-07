package com.mercadolivre.desafio_spring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersCountedDTO  extends  UserDTO{
    @JsonProperty("followers_count")
    private int followersCount;
}
