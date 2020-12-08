package myhomework;

public class Client extends Thread {

    private final DataSystem ds;
    private Request request;

    public Client(int id, DataSystem ds) {
        setName("Клиент №"+id);
        this.ds = ds;
    }


    public void createInvoice(int amount, RequestType requestType){
        this.request = new Request(getName(), amount, requestType);

    }

    @Override
    public void run() {
        printStatus();
        ds.sendInvoice(this.request);
    }

    public void printStatus(){
        String sb = currentThread().getName() +
                ": " +
                this.request.toString() +
                " отправлена в банк";
        System.out.println(sb);
    }
}
