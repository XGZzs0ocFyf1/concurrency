package myhomework;

public class RequestHandler extends Thread {

    private DataSystem ds;
    private Backend storage;

    public RequestHandler(int id, DataSystem ds, Backend storage) {
        setName("Обработчик заявок №" + id);
        this.ds = ds;
        this.storage = storage;
    }

    @Override
    public void run() {
       while (true){
           Request request = ds.receive();
           printStatus(request);
           storage.handleInvoce(request);
       }
    }

    public void printStatus(Request request){
        StringBuilder sb=  new StringBuilder();
        sb.append(currentThread().getName());
        sb.append(": ");
        sb.append(" получена заявка на обработку по клиенту");
        sb.append(request.getClientName());
        System.out.println(sb.toString());
    }
}
