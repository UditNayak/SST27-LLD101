class SimpleDecoder implements Decoder {
    @Override
    public Frame decode(byte[] data) {
        // Pretend decoding logic
        return new Frame(data);
    }
}
