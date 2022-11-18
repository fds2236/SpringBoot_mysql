package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// db 생성
// @Data로 만들면 생성자도 자동으로 만들어지는데 생성자 따로 만들기 위해 따로 따로 어노테이션씀
@Getter
@Setter
@ToString

@Entity
@Table(name="item")
public class Item {
    @Id // 프라이머리 키
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50) // nullable 널 허용 여부
    private String itemNm; // 상품명

    @Column(nullable = false, name = "price")
    private int price; // 가격
    
    @Column(nullable = false)
    private int stockNumber; // 재고 수량
    
    @Lob
    @Column(nullable = false)
    private String itemDetail; // 상품에 대한 상세 설명
    
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상태
    private LocalDateTime regTime; // 상품 등록 시간
    private LocalDateTime updateTime; // 수정 시간
}
