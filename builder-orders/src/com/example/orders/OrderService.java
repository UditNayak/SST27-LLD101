package com.example.orders;

import java.util.List;

public class OrderService {

    public Order createOrder(String id, String email, List<OrderLine> lines, Integer discount, boolean expedited, String notes) {
        return new Order.Builder()
                .id(id)
                .customerEmail(email)
                .expedited(expedited)
                .notes(notes)
                .discountPercent(discount != null ? discount : 0) // Apply default if null
                .applyLines(lines)
                .build();
    }
}
