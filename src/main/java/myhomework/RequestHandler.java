package myhomework;

public class RequestHandler extends Process implements Runnable {


    private final Backend storage;

    public RequestHandler(int id, DataSystem ds, Backend storage) {
        setName("Обработчик заявок №" + id);
        this.ds = ds;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            Request request = ds.receive();
            printStatus(request);
            storage.handleInvoce(request);
        }
    }

    public void printStatus(Request request) {
        String sb = name +
                ": " +
                " получена заявка на обработку по клиенту" +
                request.getClientName();
        System.out.println(sb);
    }
}
