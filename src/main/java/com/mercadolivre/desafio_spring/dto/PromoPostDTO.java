package com.mercadolivre.desafio_spring.dto;

import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PromoPostDTO extends PostDTO {

    @NotNull
    private Boolean hasPromo;
    @NotNull
    @Min(0)
    private Double discount;

    public PromoPostDTO(int userId, int id, String date, ProductDTO detail, int category,
                        double price, boolean hasPromo, double discount) {
        super(userId, id, date, detail, category, price);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public Post toEntity() {
        return new Post()
                .setUserId(this.getUserId())
                .setId(this.getId())
                .setDate(DateUtil.stringToDate(this.getDate()))
                .setDetail(this.getDetail().toEntity())
                .setCategory(this.getCategory())
                .setPrice(this.getPrice())
                .setHasPromo(this.hasPromo)
                .setDiscount(this.discount);
    }
}
