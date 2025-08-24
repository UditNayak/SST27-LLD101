class DefaultTaxCalculator implements TaxCalculator {
    private static final double TAX_RATE = 0.18; // 18% tax

    @Override
    public double totalWithTax(double subtotal) {
        return subtotal + (subtotal * TAX_RATE);
    }
}
