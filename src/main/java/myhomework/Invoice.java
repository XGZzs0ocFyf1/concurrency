package myhomework;

public class Invoice {
   private String clientName;
   private long amount;
   private Operation operation;

    public Invoice(String clientName, long amount, Operation operation) {
        this.clientName = clientName;
        this.amount = amount;
        this.operation = operation;
    }

    public String getClientName() {
        return clientName;
    }

    public long getAmount() {
        return amount;
    }

    public Operation getOperation() {
        return operation;
    }


    @Override
    public String toString() {
        return "Заявка{" +
                "clientTreadName ='" + clientName + '\'' +
                ", amount=" + amount +
                ", operation =" + operation +
                '}';
    }
}
