package com.mercadolivre.desafio_spring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolivre.desafio_spring.dto.PostDTO;
import com.mercadolivre.desafio_spring.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Post {

    private int userId;
    @JsonProperty("id_post")
    private int id;
    @JsonFormat(pattern = Constants.DATE_FORMAT, timezone =  Constants.DEFAULT_TIMEZONE)
    private Date date;
    private Product detail;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public PostDTO toPostDTO() {
        return new PostDTO()
                .setUserId(this.userId)
                .setId(this.id)
                .setDate(this.date)
                .setDetail(this.detail)
                .setCategory(this.category)
                .setPrice(this.price);
    }
}
