package com.dermacon.securewebapp.data;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
//    void deleteByItem_id(Long item_id);
    void deleteByItemId(Long id);
    Item findByItemId(Long id);
//    Item findByItem_name
}
