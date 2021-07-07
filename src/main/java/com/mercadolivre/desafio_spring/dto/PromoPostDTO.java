package com.mercadolivre.desafio_spring.dto;

import com.mercadolivre.desafio_spring.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PromoPostDTO extends PostDTO{
    private boolean hasPromo;
    private double discount;

    public Post toEntity() {
        return new Post()
                .setUserId(super.getUserId())
                .setId(super.getId())
                .setDate(super.getDate())
                .setDetail(super.getDetail())
                .setCategory(super.getCategory())
                .setPrice(super.getPrice())
                .setHasPromo(this.hasPromo)
                .setDiscount(this.discount);
    }
}
