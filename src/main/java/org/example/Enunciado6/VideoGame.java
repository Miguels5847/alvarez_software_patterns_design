package org.example.Enunciado6;
//PATRON:FACTORY
//ENUNCIADO :En un videojuego, los enemigos pueden comportarse de forma agresiva, defensiva o aleatoria,
// y ese comportamiento debe poder cambiar mientras la partida esta en curso.

interface Behavior {
    void act();
}

class Aggressive implements Behavior {
    public void act(){System.out.println("Enemigo agresivo: atacando al jugador");}
}
class Defensive implements Behavior {
    public void act(){System.out.println("Enemigo defensivo: bloqueando ataques");}
}
class RandomBehavior implements Behavior {
    public void act(){System.out.println("Enemigo aleatorio: comportamiento impredecible");}
}

//Creator
abstract class EnemyFlow {
    protected abstract Behavior createBehavior(); //Factory Method
    public void play(){
        Behavior b = createBehavior();
        System.out.println("Iniciando comportamiento...");
        b.act();
    }
}

class AggressiveEnemy extends EnemyFlow {
    protected Behavior createBehavior(){return new Aggressive();}
}
class DefensiveEnemy extends EnemyFlow {
    protected Behavior createBehavior(){return new Defensive();}
}
class RandomEnemy extends EnemyFlow {
    protected Behavior createBehavior(){return new RandomBehavior();}
}

public class VideoGame {
    public static void main(String[] args){
        EnemyFlow enemy = new AggressiveEnemy();
        enemy.play();

        enemy = new DefensiveEnemy();
        enemy.play();

        enemy = new RandomEnemy();
        enemy.play();
    }
}
