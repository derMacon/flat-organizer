package com.dermacon.securewebapp.controller.services;

import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Iterable<Item> getSortedItems_nextPurchase() {
        return sort(itemRepository.findAllByStatus(false));
    }

    public Iterable<Item> getSortedItems_prevPurchase() {
        return sort(itemRepository.findAllByStatus(true));
    }

    /**
     * Sorts a given iterable instance
     * @param it iterable instance to sort
     * @param <T> element type of the iterable elements
     * @return sorted Iterable instance
     */
    private Iterable<Item> sort(Iterable<Item> it) {
        Stream<Item> stream = StreamSupport.stream(it.spliterator(), false);
        return stream.sorted().collect(Collectors.toList());
    }


}
