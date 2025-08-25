public class Demo08 {
    public static void main(String[] args) {
        PedalVehicle bike = new Bicycle();
        bike.pedal(5);

        EngineVehicle car = new Car();
        car.startEngine();

        ElectricVehicle eBike = new E_Bike();
        eBike.recharge(10);
    }
}