package myhomework;

public class Client extends Thread {

    private DataSystem ds;
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
        StringBuilder sb=  new StringBuilder();
        sb.append(currentThread().getName());
        sb.append(": ");
        sb.append(this.request.toString());
        sb.append(" отправлена в банк");
        System.out.println(sb.toString());
    }
}
