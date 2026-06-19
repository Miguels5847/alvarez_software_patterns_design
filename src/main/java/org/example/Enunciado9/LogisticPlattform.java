package org.example.Enunciado9;
//PATRON:FACTORY
//ENUNCIADO: Una plataforma de logistica debe usar el transportista adecuado (terrestre, maritimo o aereo) segun la ruta.
// La planificacion del envio es siempre la misma; lo unico que varia es el medio que se instancia.

interface Transport {
    void deliver();
}

class Truck implements Transport {
    public void deliver(){System.out.println("Entrega terrestre via camion");}
}
class Ship implements Transport {
    public void deliver(){System.out.println("Entrega maritima via barco");}
}
class Plane implements Transport {
    public void deliver(){System.out.println("Entrega aerea via avion");}
}

//Creator
abstract class Logistics {
    protected abstract Transport createTransport(); //Factory Method
    public void planDelivery(){
        Transport t = createTransport();
        System.out.println("Planificando envio...");
        t.deliver();
    }
}

class RoadLogistics extends Logistics {
    protected Transport createTransport(){return new Truck();}
}
class SeaLogistics extends Logistics {
    protected Transport createTransport(){return new Ship();}
}
class AirLogistics extends Logistics {
    protected Transport createTransport(){return new Plane();}
}

public class LogisticPlattform {
    public static void main(String[] args){
        Logistics road = new RoadLogistics();
        Logistics sea = new SeaLogistics();
        Logistics air = new AirLogistics();

        road.planDelivery();
        sea.planDelivery();
        air.planDelivery();
    }
}
