public class Player {
    private Decoder decoder;
    private Renderer renderer;
    private Cache cache;

    public Player(Decoder decoder, Renderer renderer, Cache cache) {
        this.decoder = decoder;
        this.renderer = renderer;
        this.cache = cache;
    }

    void play(byte[] fileBytes) {
        Frame f = decoder.decode(fileBytes);
        renderer.render(f);
        cache.store(f);
        System.out.println("Cached last frame? " + cache.hasLastFrame());
    }
}