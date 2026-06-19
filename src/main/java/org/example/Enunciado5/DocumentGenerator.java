package org.example.Enunciado5;
//PATRON: FACTORY
//ENUNCIADO : Un editor genera distintos tipos de documento (carta, factura, curriculum) segun lo que elige el usuario.
// El flujo de "crear, configurar y abrir" es identico para todos; solo cambia el documento concreto que se produce.

interface Document {
    void create();
}

class Letter implements Document {
    public void create(){System.out.println("Creando documento: carta");}
}
class Invoice implements Document {
    public void create(){System.out.println("Creando documento: factura");}
}
class Resume implements Document {
    public void create(){System.out.println("Creando documento: curriculum");}
}

//Creator
abstract class DocumentEditor {
    protected abstract Document createDocument(); //Factory Method
    public void open(){
        Document doc = createDocument();
        System.out.println("Iniciando flujo...");
        doc.create();
        System.out.println("Configurando y abriendo documento");
    }
}

class LetterEditor extends DocumentEditor {
    protected Document createDocument(){return new Letter();}
}
class InvoiceEditor extends DocumentEditor {
    protected Document createDocument(){return new Invoice();}
}
class ResumeEditor extends DocumentEditor {
    protected Document createDocument(){return new Resume();}
}

public class DocumentGenerator {
    public static void main(String[] args){
        DocumentEditor letter = new LetterEditor();
        DocumentEditor invoice = new InvoiceEditor();
        DocumentEditor resume = new ResumeEditor();

        letter.open();
        invoice.open();
        resume.open();
    }
}
