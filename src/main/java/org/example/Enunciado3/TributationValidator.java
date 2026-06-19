package org.example.Enunciado3;

// PATRON: ADAPTER
// Enunciado: Tu sistema de facturacion usa una interfaz propia, pero debes integrar un servicio de validacion tributaria del gobierno con
// una firma y formato totalmente distintos, y NO puedes modificar ese servicio.
class Invoice {
    String customerId;
    double total;
    String invoiceId;

    public Invoice(String customerId, double total, String invoiceId) {
        this.customerId = customerId;
        this.total = total;
        this.invoiceId = invoiceId;
    }
}

interface TaxValidator {
    boolean validate(Invoice invoice);
}

class GovTaxService {
    // Devuelve: 0 = ok, 1 = invalido, 2 = bloqueado.
    int verifyTaxDocument(String taxpayerNumber, long amountCents,
                          String docType, String fiscalYear) {
        System.out.println("GOV: verificando " + taxpayerNumber
                + " monto=" + amountCents + " centavos"
                + " tipo=" + docType + " anio=" + fiscalYear);
        return 0; // simulamos validacion ok
    }
}

class GovTaxValidatorAdapter implements TaxValidator {
    private final GovTaxService gov;
    public GovTaxValidatorAdapter(GovTaxService gov) {
        this.gov = gov;
    }

    @Override
    public boolean validate(Invoice invoice) {
        long cents = Math.round(invoice.total * 100);
        String year = String.valueOf(java.time.Year.now().getValue());
        String docType = "INVOICE";
        int code = gov.verifyTaxDocument(invoice.customerId, cents, docType, year);
        return code == 0;
    }
}


public class TributationValidator {
    public static void main(String[] args) {
        // El cliente usa el Target. El Adapter envuelve al Adaptee.
        TaxValidator validator = new GovTaxValidatorAdapter(new GovTaxService());

        Invoice inv = new Invoice("RUC-1234567", 250.00, "F-001");
        boolean ok = validator.validate(inv);

        System.out.println("Factura " + inv.invoiceId + " valida? " + ok);
    }
}
