package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  private List<PromoPostDTO> promos = new ArrayList<>();

  public UserPromosDTO(int userId, String userName, List<PromoPostDTO> promos) {
    super(userId, userName);
    this.promos = promos;
  }
}
