package org.example.Enunciado8;
//PATRON: Adapter
//ENUNCIADO: Quieres reutilizar una libreria de graficos antigua que dibuja con metodos drawLine y drawCircle,
// pero tu aplicacion nueva trabaja con objetos que exponen render().

interface Renderable {
    void render();
}

class OldGraphicsLib {
    void drawLine(int x1, int y1, int x2, int y2){
        System.out.println("OldLib drawLine: (" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")");
    }
    void drawCircle(int x, int y, int radius){
        System.out.println("OldLib drawCircle: centro (" + x + "," + y + ") radio " + radius);
    }
}

class LineAdapter implements Renderable {
    private final OldGraphicsLib lib;

    public LineAdapter(OldGraphicsLib lib){
        this.lib = lib;
    }

    @Override
    public void render(){
        lib.drawLine(0, 0, 100, 100);
    }
}

class CircleAdapter implements Renderable {
    private final OldGraphicsLib lib;

    public CircleAdapter(OldGraphicsLib lib){
        this.lib = lib;
    }

    @Override
    public void render(){
        lib.drawCircle(50, 50, 25);
    }
}

public class Graphics {
    public static void main(String[] args){
        OldGraphicsLib lib = new OldGraphicsLib();
        Renderable line = new LineAdapter(lib);
        Renderable circle = new CircleAdapter(lib);

        line.render();
        circle.render();
    }
}
