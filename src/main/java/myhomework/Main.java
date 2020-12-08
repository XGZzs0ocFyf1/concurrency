package myhomework;

public class Main {

    public static void main(String[] args) {

        DataSystem ds = new DataSystem();
        Backend storage = new Backend();

        Client client1 = new Client(1, ds);
        Client client2 = new Client(2, ds);
        Client client3 = new Client(3, ds);
        Client client4 = new Client(4, ds);
        Client client5 = new Client(5, ds);

        client1.createInvoice(1000, RequestType.PAYMENT);
        client2.createInvoice(2000, RequestType.PAYMENT);
        client3.createInvoice(3000, RequestType.PAYMENT);
        client4.createInvoice(4000, RequestType.PAYMENT);
        client5.createInvoice(25000, RequestType.CREDIT);


        RequestHandler requestHandler1 = new RequestHandler(1, ds, storage);
        RequestHandler requestHandler2 = new RequestHandler(2, ds, storage);

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        requestHandler1.start();
        requestHandler2.start();

    }


    /***
     * Клиент №1: Заявка{clientTreadName ='Клиент №1', amount=1000, operation =PAYMENT} отправлена в банк
     * Обработчик заявок №2:  получена заявка на обработку по клиентуКлиент №1
     * Клиент №2: Заявка{clientTreadName ='Клиент №2', amount=2000, operation =PAYMENT} отправлена в банк
     * Бэк система: Заявка{clientTreadName ='Клиент №1', amount=1000, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от Обработчик заявок №2. Баланс банка: 1000
     * Клиент №3: Заявка{clientTreadName ='Клиент №3', amount=3000, operation =PAYMENT} отправлена в банк
     * Обработчик заявок №2:  получена заявка на обработку по клиентуКлиент №2
     * Бэк система: Заявка{clientTreadName ='Клиент №2', amount=2000, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от Обработчик заявок №2. Баланс банка: 3000
     * Обработчик заявок №1:  получена заявка на обработку по клиентуКлиент №3
     * Бэк система: Заявка{clientTreadName ='Клиент №3', amount=3000, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от Обработчик заявок №1. Баланс банка: 6000
     * Клиент №5: Заявка{clientTreadName ='Клиент №5', amount=25000, operation =CREDIT} отправлена в банк
     * Клиент №4: Заявка{clientTreadName ='Клиент №4', amount=4000, operation =PAYMENT} отправлена в банк
     * Обработчик заявок №2:  получена заявка на обработку по клиентуКлиент №5
     * Обработчик заявок №1:  получена заявка на обработку по клиентуКлиент №4
     * Бэк система: Заявка{clientTreadName ='Клиент №5', amount=25000, operation =CREDIT} НЕ ВЫПОЛНЕНА; получена от Обработчик заявок №2; cумма больше баланса банка. Баланс банка: 6000
     * Бэк система: Заявка{clientTreadName ='Клиент №4', amount=4000, operation =PAYMENT} УСПЕШНО ВЫПОЛНЕНА; получена от Обработчик заявок №1. Баланс банка: 10000
     */

}
