package com.mercadolivre.desafio_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mercadolivre.desafio_spring.entity.Post;
import com.mercadolivre.desafio_spring.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonPropertyOrder({ "id_post", "date", "detail", "category", "price"})
public class PostDTO {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userId;
    @NotNull
    @JsonProperty("id_post")
    private Integer id;
    @NotNull
    @Pattern(regexp = DateUtil.DATE_REGEX)
    private String date;
    @Valid
    @NotNull
    private ProductDTO detail;
    @NotNull
    private Integer category;
    @NotNull
    @Min(0)
    private Double price;

    public Post toEntity() {
        return new Post()
                .setUserId(this.userId)
                .setId(this.id)
                .setDate(DateUtil.stringToDate(this.date))
                .setDetail(this.detail.toEntity())
                .setCategory(this.category)
                .setPrice(this.price);
    }
}
