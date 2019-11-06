package task3;

import java.util.ArrayList;
import java.util.List;

public class SuperExstends {
    public static void main(String[] args) {
        Service<IVehicle> service = new Service<>();
        service.workList.add(new Truck());
//        service.workList.add(new Airplane());
    }

}

interface IVehicle {
}

class Truck implements IVehicle {
}

class Airplane {
}

class Service<T extends IVehicle> { // нельзя написать <T super IVehicle>
    List<T> workList = new ArrayList<>();

}
