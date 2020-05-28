package main.Model;

//public class MyTimerThread extends Thread {
//}
    public class MyTimerThread implements Runnable {


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10*1000);
                System.out.println("Neatza, m-am trezit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
