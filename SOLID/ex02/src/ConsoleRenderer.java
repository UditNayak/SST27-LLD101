public class ConsoleRenderer implements Renderer {
    @Override
    public void render(Frame frame) {
        System.out.println("\u25B6 Playing " + frame.getData().length + " bytes");
    }
}
