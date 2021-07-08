package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserPromosDTO extends UserDTO{

  @JsonProperty("posts")
  private List<Post> promos = new ArrayList<>();

  public UserPromosDTO(int userId, String userName, List<Post> promos) {
    super(userId, userName);
    this.promos = promos;
  }
}
