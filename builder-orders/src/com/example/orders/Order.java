package com.example.orders;

import java.util.ArrayList;
import java.util.List;

/*
 Objective1: Externally Immutable Order (no setters, unmodifiable lines, defensive copies of lines)
 Objective2: Builder Pattern
 Objective3: Centralized Validation in build()
 */

 /*
  Immutable Class
  - private final fields
  - constructor injection (with deep copy / defensive copies)
  - no setters
  - return deep copies in getters
  - mark the class as final to prevent subclassing
  */

public final class Order {
    private final String id;                // required
    private final String customerEmail;     // required
    private final List<OrderLine> lines;   // at least 1 line required (size > 0)
    private final Integer discountPercent; // 0..100 expected, but not enforced
    private final boolean expedited;
    private final String notes;

    // Private constructor accepts Builder
    private Order(Builder builder) {
        this.id = builder.id;
        this.customerEmail = builder.customerEmail;
        this.lines = new ArrayList<>();    // deep copy
        for (OrderLine line : builder.lines) {
            this.lines.add(new OrderLine(line.getSku(), line.getQuantity(), line.getUnitPriceCents()));
        }
        this.discountPercent = builder.discountPercent;
        this.expedited = builder.expedited;
        this.notes = builder.notes;
    }

    // Getters (no setters)
    public String getId() { return this.id; }
    public String getCustomerEmail() { return this.customerEmail; }
    public Integer getDiscountPercent() { return this.discountPercent; }
    public boolean isExpedited() { return this.expedited; }
    public String getNotes() { return this.notes; }
    public List<OrderLine> getLines() {
        List<OrderLine> copy = new ArrayList<>();    // deep copy
        for (OrderLine line : this.lines) {
            copy.add(new OrderLine(line.getSku(), line.getQuantity(), line.getUnitPriceCents()));
        }
        return copy;
    }

    public int totalBeforeDiscount() {
        int sum = 0;
        for (OrderLine l : lines) sum += l.getQuantity() * l.getUnitPriceCents();
        return sum;
    }

    public int totalAfterDiscount() {
        int base = totalBeforeDiscount();
        if (discountPercent == null) return base;
        return base - (base * discountPercent / 100);
    }

    /*
     Builder Class
     - inner static Builder class with same attributes as of parent (all mutable --> No Final)
     - private constructor that accept a Builder class
     - All setters return this (the Builder)
     - build() method to create the immutable Order instance with centralized validation
     */
    public static class Builder{
        private String id;
        private String customerEmail;
        private List<OrderLine> lines = new ArrayList<>();
        private Integer discountPercent;
        private boolean expedited;
        private String notes;

        // Setters (return this)
        public Builder id(String id){ this.id = id; return this; }
        public Builder customerEmail(String customerEmail){ this.customerEmail = customerEmail; return this; }
        public Builder discountPercent(Integer discountPercent){ this.discountPercent = discountPercent; return this; }
        public Builder expedited(boolean expedited){ this.expedited = expedited; return this; }
        public Builder notes(String notes){ this.notes = notes; return this; }
        public Builder addLine(OrderLine line){ if (line != null) this.lines.add(line); return this; }
        public Builder applyLines(List<OrderLine> lines) {
            if (lines != null) {
                for (OrderLine line : lines) {
                    addLine(line);
                }
            }
            return this;
        }


        public Order build() {
            if (!PricingRules.isValidEmail(customerEmail)) {
                throw new IllegalArgumentException("Invalid email: " + customerEmail);
            }
            if (!PricingRules.isValidDiscount(discountPercent)) {
                throw new IllegalArgumentException("Invalid discount: " + discountPercent);
            }
            if (lines.isEmpty()) {
                throw new IllegalStateException("At least one order line is required");
            }
            return new Order(this);
        }
    }
}
