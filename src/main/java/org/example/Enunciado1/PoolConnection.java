package org.example.Enunciado1;
//PATRON; SINGLETON
// Ejercicio ;
//En tu aplicación hay un pool de conexiones a base de datos costoso de crear.
// Varios módulos lo necesitan y todos deben trabajar contra el mismo recurso compartido. Diséñalo.
class ConecctionPool {
    private final String PoolConnection;
    private int activeConnections = 0;

    private ConecctionPool(){
        this.PoolConnection = "Api key de la pool de conexiones de base de datos";
        System.out.println("Inicializando el pool de conexiones a la base de datos");
    }

    private static class Holder {
        private static final ConecctionPool INSTANCE = new ConecctionPool();
    }
    public static ConecctionPool getInstance() {return Holder.INSTANCE;}

    public void charge(boolean estado){
        activeConnections++;
        System.out.println("Estado de conexion "+ estado + " (conexiones reutilizadas " +activeConnections +")");
    }

}

public class PoolConnection {
    public static void main (String[] args){
        ConecctionPool.getInstance().charge(true);
        ConecctionPool.getInstance().charge(true);
        ConecctionPool.getInstance().charge(false);
        //Prueba de singleton
        ConecctionPool p1 = ConecctionPool.getInstance();
        ConecctionPool p2 = ConecctionPool.getInstance();
        System.out.println("Es la misma instancia? "+ (p1 == p2));
        p1.charge(true);
        p2.charge(true);
        p1.charge(false);

    }
}
