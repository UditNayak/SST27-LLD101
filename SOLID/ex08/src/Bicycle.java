public class Bicycle implements PedalVehicle {
    @Override
    public void pedal(int effort) {
        System.out.println("Pedaling with effort: " + effort);
    }
}