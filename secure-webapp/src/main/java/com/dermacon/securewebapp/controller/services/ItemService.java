package com.dermacon.securewebapp.controller.services;

import com.dermacon.securewebapp.data.Item;
import com.dermacon.securewebapp.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<String> getSortedItems_nextPurchase() {
        return sort(itemRepository.findAllByStatus(false));
    }

    public List<String> getSortedItems_prevPurchase() {
        return sort(itemRepository.findAllByStatus(true));
    }

    /**
     * Sorts a given iterable instance
     * @param it iterable instance to sort
     * @param <T> element type of the iterable elements
     * @return sorted Iterable instance
     */
    private List<String> sort(Iterable<Item> it) {
        Stream<Item> stream = StreamSupport.stream(it.spliterator(), false);
        return stream.sorted().map(Item::toString).collect(Collectors.toList());
    }


}
