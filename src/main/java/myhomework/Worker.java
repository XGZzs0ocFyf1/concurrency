package myhomework;

public class Worker extends Thread {

    private DataSystem ds;
    private Backend storage;

    public Worker(int id, DataSystem ds, Backend storage) {
        setName("Обработчик заявок №" + id);
        this.ds = ds;
        this.storage = storage;
    }

    public Invoice getInvoice() {
        return ds.receive();
    }

    @Override
    public void run() {
       while (true){
           Invoice invoice = ds.receive();
           storage.handleInvoce(invoice);

       }
        //printStatus(invoice);
    }

    public void printStatus(Invoice invoice){
        StringBuilder sb=  new StringBuilder();
        sb.append(currentThread().getName());
        sb.append(": ");
        sb.append(" получена заявка на обработку по клиенту");
        sb.append(invoice.getClientName());
        System.out.println(sb.toString());
    }
}
