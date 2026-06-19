package org.example.Enunciado2;
//PATRON: Factory
//Enunciado : Una tienda calcula el precio final aplicando promociones que cambian por temporada (Black Friday, 2x1, sin promo).
// Marketing quiere poder añadir promos nuevas sin reescribir el checkout.

interface Discounts {
    void discount(double amount);
}

class DiscountBlackFriday implements Discounts{
    public void discount(double a) {System.out.println("Descuento del black friday: %" + a);}
}
class Discount2x1 implements Discounts{
    public void discount(double a) {System.out.println("Descuento del 2x1 : %" + a);}
}
class NoDiscount implements Discounts {
    public void discount (double a){System.out.println("No hay Promo ni descuentos");}
}
//Creator
abstract class DiscountFlow {
    protected abstract Discounts createDiscounts(); //Factory Method
    public void Discount(double amount){
    Discounts method = createDiscounts();
    System.out.println("Iniciando descuentos...");
    method.discount(amount);
    }
}
class BlackFridayDiscount extends DiscountFlow {
    protected Discounts createDiscounts(){return new DiscountBlackFriday();}
}

class Discount2x1Flow extends DiscountFlow{
    protected Discounts createDiscounts(){return new Discount2x1();}
}
class NoDiscountFlow extends DiscountFlow{
    protected Discounts createDiscounts(){return new NoDiscount();}
}
public class BlackFriday {
    public static void main(String[] args){
        DiscountFlow blackfriday = new BlackFridayDiscount();
        DiscountFlow dosx1 = new Discount2x1Flow();
        DiscountFlow nodiscount = new NoDiscountFlow();

        blackfriday.Discount(20.00);
        dosx1.Discount(30.00);
        nodiscount.Discount(00.00);
    }
}
