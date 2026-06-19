package org.example.Enunciado10;
//PATRON:ABSTRACT FACTORY
//ENUNCIADO: Un sistema multiplataforma debe generar controles de interfaz (botones, checkboxes, menus)
// que se vean nativos en Windows, macOS y Linux, garantizando que nunca se mezclen estilos de distintos sistemas operativos.

interface Button {
    void paint();
}
interface Checkbox {
    void paint();
}
interface Menu {
    void paint();
}

//Familia Windows
class WindowsButton implements Button {
    @Override
    public void paint(){System.out.println("Boton estilo Windows");}
}
class WindowsCheckbox implements Checkbox {
    @Override
    public void paint(){System.out.println("Checkbox estilo Windows");}
}
class WindowsMenu implements Menu {
    @Override
    public void paint(){System.out.println("Menu estilo Windows");}
}

//Familia macOS
class MacButton implements Button {
    @Override
    public void paint(){System.out.println("Boton estilo macOS");}
}
class MacCheckbox implements Checkbox {
    @Override
    public void paint(){System.out.println("Checkbox estilo macOS");}
}
class MacMenu implements Menu {
    @Override
    public void paint(){System.out.println("Menu estilo macOS");}
}

//Familia Linux
class LinuxButton implements Button {
    @Override
    public void paint(){System.out.println("Boton estilo Linux");}
}
class LinuxCheckbox implements Checkbox {
    @Override
    public void paint(){System.out.println("Checkbox estilo Linux");}
}
class LinuxMenu implements Menu {
    @Override
    public void paint(){System.out.println("Menu estilo Linux");}
}

//Fabrica abstracta
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
    Menu createMenu();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton(){return new WindowsButton();}
    @Override
    public Checkbox createCheckbox(){return new WindowsCheckbox();}
    @Override
    public Menu createMenu(){return new WindowsMenu();}
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton(){return new MacButton();}
    @Override
    public Checkbox createCheckbox(){return new MacCheckbox();}
    @Override
    public Menu createMenu(){return new MacMenu();}
}

class LinuxFactory implements GUIFactory {
    @Override
    public Button createButton(){return new LinuxButton();}
    @Override
    public Checkbox createCheckbox(){return new LinuxCheckbox();}
    @Override
    public Menu createMenu(){return new LinuxMenu();}
}

//cliente: trabaja con la familia sin saber cual es
class GUIRenderer {
    private final Button button;
    private final Checkbox checkbox;
    private final Menu menu;

    public GUIRenderer(GUIFactory factory){
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
        this.menu = factory.createMenu();
    }

    void render(){
        button.paint();
        checkbox.paint();
        menu.paint();
    }
}

public class InterfaceControl {
    public static void main(String[] args){
        String os = "windows";
        GUIFactory factory;
        if (os.equals("windows")) factory = new WindowsFactory();
        else if (os.equals("mac")) factory = new MacFactory();
        else factory = new LinuxFactory();

        new GUIRenderer(factory).render();
    }
}
