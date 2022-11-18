package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
// 완벽하게 추상화관계 => Repository만 검증하는 것
@SpringBootTest
@Slf4j // xml파일을 따로 만다는 대신 어노테이션으로 해결
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository; // 의존성 주입
    // private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("아메리카노" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("과테말라에서 수입한 원두로 만든 아메리카노입니다." + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
//          Item savedItem = itemRepository.save(item);
            itemRepository.save(item);
            log.error(item.toString());
        }
    }


    @Test
    @DisplayName("상품 조회 테스트")
    public void findByItemNmTest() {
        this.createItemTest(); // 메소드 호출
        List<Item> itemList = itemRepository.findByItemNmLike("%" + "아메" + "%");
        for(Item item : itemList) {
            log.error(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명")
    public void findByItemNmOrItemDetailTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository
                .findByItemNmOrItemDetail("아메리카노3", "dk");
        for(Item item : itemList) {
            log.error(item.toString());
        }
    }

    @Test
    @DisplayName("입력받은 가격보다 싼 제품 찾기")
    public void findByPriceLessThanTest(){
        this.createItemTest();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList) {
            log.error(item.toString());
        }
    }

    
}