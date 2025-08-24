public class Demo02 {
    public static void main(String[] args) {
        Decoder decoder = new SimpleDecoder();
        Renderer renderer = new ConsoleRenderer();
        Cache cache = new MemoryCache();
        Player player = new Player(decoder, renderer, cache);
        player.play(new byte[]{1, 2, 3, 4});
    }
}
