package myhomework;

public class Client extends Thread {

    private DataSystem ds;
    private Invoice invoice;

    public Client(int id, DataSystem ds) {
        setName("Клиент №"+id);
        this.ds = ds;
    }


    public void createInvoice(int amount, Operation operation){
        this.invoice = new Invoice(getName(), amount, operation);
    }



    @Override
    public void run() {
        ds.sendInvoice(this.invoice);
    }

    public void printStatus(){
        StringBuilder sb=  new StringBuilder();
        sb.append(currentThread().getName());
        sb.append(": ");
        sb.append(this.invoice.toString());
        sb.append(" отправлена в банк");
        System.out.println(sb.toString());
    }
}
