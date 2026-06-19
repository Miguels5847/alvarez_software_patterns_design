package org.example.Enunciado4;
//PATRON:Abstract Factory
//ENUNCIADO : Tu app debe poder desplegarse tanto en AWS como en Azure. Cada nube ofrece su propio almacenamiento,
// su cola de mensajes y su base de datos, y bajo ninguna circunstancia deben combinarse piezas de proveedores distintos.
interface Deploy{
    void deploy(boolean estado);
}
interface NameCloud {
    boolean validate (String nombreCloud);
}
//Familia AWS
class AwsDeploy implements Deploy{
    @Override
    public void deploy(boolean estado) {System.out.println("Iniciando deploy en AWS ");}

}
class AWSNameCloud implements NameCloud{
    @Override
    public boolean validate(String nombreCloud) {
        return true;
    }
}
//Familia AZURE
class AzureDeploy implements Deploy{
    @Override
    public void deploy(boolean estado) {System.out.println("Iniciando deploy en Azure ");}

}
class AzureNameCloud implements NameCloud{
    @Override
    public boolean validate(String nombreCloud) {
        return false;
    }
}
//fabrica abstracta
interface DeployFactory{
    Deploy createDeploy();
    NameCloud createNameCloud();
}

class AWSCloud implements DeployFactory {
    @Override
    public Deploy createDeploy() {
        return new AwsDeploy();
    }

    @Override
    public NameCloud createNameCloud() {
        return new AWSNameCloud();
    }
}
class AzureCloud implements DeployFactory{
    @Override
    public Deploy createDeploy() {
        return new AzureDeploy();
    }

    @Override
    public NameCloud createNameCloud() {
        return new AzureNameCloud();
    }
}

//cliente: trabaja con la familia sin saber cual es
class DeployCloudService{
    private final Deploy deploy;
    private final NameCloud nameCloud;

    public DeployCloudService(DeployFactory factory){
        this.deploy = factory.createDeploy();
        this.nameCloud = factory.createNameCloud();
    }

    void process(String nombreCloud, boolean estado){
        if (nameCloud.validate(nombreCloud)) deploy.deploy(estado);
    }
}

public class Cloud {
    public static void main(String[] args) {
        String nombreCloud = "AWS";
        DeployFactory deployFactory = nombreCloud.equals("AWS") ? new AWSCloud() : new AzureCloud();
        new DeployCloudService(deployFactory).process("AWS",true);
    }
}
