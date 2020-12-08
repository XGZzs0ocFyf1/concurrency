package myhomework;

public class Request {
   private String clientName;
   private long amount;
   private RequestType requestType;

    public Request(String clientName, long amount, RequestType requestType) {
        this.clientName = clientName;
        this.amount = amount;
        this.requestType = requestType;
    }

    public String getClientName() {
        return clientName;
    }

    public long getAmount() {
        return amount;
    }

    public RequestType getOperation() {
        return requestType;
    }


    @Override
    public String toString() {
        return "Заявка{" +
                "clientTreadName ='" + clientName + '\'' +
                ", amount=" + amount +
                ", operation =" + requestType +
                '}';
    }
}
