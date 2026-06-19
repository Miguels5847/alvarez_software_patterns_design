package org.example.Enunciado7;
//PATRON:SINGLETON
//ENUNCIADO: Necesitas un registro central de metricas (contadores y tiempos) que toda la aplicacion alimente
// y del que exista un unico punto de verdad accesible globalmente.

class MetricsRegistry {
    private int counter = 0;
    private long totalTime = 0;

    private MetricsRegistry(){
        System.out.println("Inicializando el registro central de metricas");
    }

    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }
    public static MetricsRegistry getInstance(){return Holder.INSTANCE;}

    public void incrementCounter(){
        counter++;
        System.out.println("Contador: " + counter);
    }

    public void addTime(long ms){
        totalTime += ms;
        System.out.println("Tiempo total acumulado: " + totalTime + " ms");
    }
}

public class Metrics {
    public static void main(String[] args){
        MetricsRegistry.getInstance().incrementCounter();
        MetricsRegistry.getInstance().incrementCounter();
        MetricsRegistry.getInstance().addTime(120);
        MetricsRegistry.getInstance().addTime(80);

        MetricsRegistry m1 = MetricsRegistry.getInstance();
        MetricsRegistry m2 = MetricsRegistry.getInstance();
        System.out.println("Es la misma instancia? " + (m1 == m2));
    }
}
