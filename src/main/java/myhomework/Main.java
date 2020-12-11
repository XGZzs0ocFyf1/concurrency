package myhomework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private final static ExecutorService  clients = Executors.newFixedThreadPool(5);
    private final static ExecutorService handlers = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        DataSystem ds = new DataSystem();
        Backend storage = new Backend();


        for (int i = 0; i < 5; i++) {
            Client c = new Client(i, ds);
            c.createInvoice(ThreadLocalRandom.current().nextInt(20000), RequestType.PAYMENT);
            clients.execute(c);
        }

        for (int i = 0; i < 2; i++) {
            RequestHandler handler = new RequestHandler(i, ds, storage);
            handlers.execute(handler);
        }

        clients.shutdown();


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
