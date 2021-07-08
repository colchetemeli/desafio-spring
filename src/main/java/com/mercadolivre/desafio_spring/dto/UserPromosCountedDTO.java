package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPromosCountedDTO extends UserDTO{

    public UserPromosCountedDTO(int userId, String userName, int promoProducts) {
        super(userId, userName);
        this.promoProducts = promoProducts;
    }

    @JsonProperty("promoproducts_count")
    private int promoProducts;
}
