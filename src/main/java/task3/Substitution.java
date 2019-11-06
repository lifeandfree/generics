package task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Функции, которые используют базовый тип,
 * должны иметь возможность использовать подтипы базового типа, не зная об этом
 * Liskov Substitution
 */
public class Substitution {

    List<Vehicle> list = new ArrayList<>();

    {
        list.add(new Vehicle());
        list.add(new Car());
        list.add(new Bus());
    }

    public static void main(String[] args) {

        for (Vehicle vehicle : new Substitution().list) {
            System.out.println(vehicle);
        }
    }

}

class Vehicle {
}

class Car extends Vehicle {
}

class Bus extends Vehicle {
}

