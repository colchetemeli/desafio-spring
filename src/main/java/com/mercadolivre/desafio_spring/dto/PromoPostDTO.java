package com.mercadolivre.desafio_spring.dto;

import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)

public class PromoPostDTO extends PostDTO {

    private boolean hasPromo;
    private double discount;

    public PromoPostDTO(int userId, int id, Date date, Product detail, int category,
                        double price, boolean hasPromo, double discount) {
        super(userId, id, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Post toEntity() {
        return new Post()
                .setUserId(this.getUserId())
                .setId(this.getId())
                .setDate(this.getDate())
                .setDetail(this.getDetail())
                .setCategory(this.getCategory())
                .setPrice(this.getPrice())
                .setHasPromo(this.hasPromo)
                .setDiscount(this.discount);
    }
}
