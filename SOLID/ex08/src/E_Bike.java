public class E_Bike implements EngineVehicle, ElectricVehicle {
    @Override
    public void startEngine() {
        System.out.println("E-Bike engine started");
    }

    @Override
    public void recharge(int kWh) {
        System.out.println("Recharging E-Bike with " + kWh + " kWh");
    }
}