class MemoryCache implements Cache {
    private Frame lastFrame;

    @Override
    public void store(Frame frame) {
        lastFrame = frame;
    }

    @Override
    public boolean hasLastFrame() {
        return lastFrame != null;
    }
}
