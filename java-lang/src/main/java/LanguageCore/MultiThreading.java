package LanguageCore;

// Onenote: Multi-Threading
// Onenote: More on thread priority
public class MultiThreading {

    // A thread group is a data structure that controls the state of a collection of threads as a whole
    private static String THREAD_OUTPUT_DESCRIPTION = "[name of thread, its priority, and name of its group] = ";

    public static void main(){

        //Although the main thread is created automatically when your program is started, it can be controlled through a Thread object
        // This method returns a reference to the thread in which it is called.
        Thread t = Thread.currentThread();
        System.out.println(THREAD_OUTPUT_DESCRIPTION + t);

        // call run() on ThreadDemo2 object, passing in "this" object as first arg is acceptable
        t = new Thread(new ThreadDemo2(), "ThreadDemo2");  // Thread(Runnable threadOb, String threadName)
        // After the new thread is created, it will not start running until you call its start( ) method,
        // which is declared within Thread. In essence, start( ) executes a call to run
        //imp: It is never legal to start a thread more than once. In particular, a thread may not be restarted once it has completed execution.
        //imp: calling t.run() will run t's task on the current thread[synchronously (in the same thread)], and t.start() will run t's task on thread t itself
        t.start();
        Thread thread2 = new ThreadDemo();

        try {
            for(int n = 5; n > 0; n--) {
                System.out.println(Thread.currentThread().getName() + " : " + n);
                // causes the thread from which it is called to suspend execution for the specified period of time
                Thread.sleep(500);
            }
        }
        //imp: The sleep() method in Thread might throw an InterruptedException. This would happen if some other thread wanted to interrupt this thread while it is sleeping
        // Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted, either before or during the activity.
        // Occasionally a method may wish to test whether the current thread has been interrupted, and if so, to immediately throw this exception
        catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        //isAlive( ) method returns true if the thread upon which it is called is still running. It returns false otherwise
        System.out.println("Is " + t + " alive :" + t.isAlive());

        //join() method waits until the thread on which it is called terminates. Code below waits until all children threads have joined the main thread
        // code below will ensure that the main(parent) thread is the last one to stop
        try{
            thread2.join();
            t.join(5000);
            System.out.println("can't wait for more than 5000 millis");
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Main thread about to end");
    }

    //imp: Two ways of creating a Thread: 1. Implement the Runnable interface, 2. Extend the Thread class, itself.
    private static class ThreadDemo extends Thread {

        public ThreadDemo(){
            super("ThreadDemo");   // This invokes the following form of the Thread constructor: public Thread(String threadName)
            start();    // start thread (call's run)
        }

        @Override
        public void run() {
            try {
                for(int n = 5; n > 0; n--) {
                    System.out.println(Thread.currentThread().getName() + " : " + n);
                    Thread.sleep(500);
                }
            } catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " thread interrupted");
            }
        }
    }

    private static class ThreadDemo2 implements Runnable {

        // run() establishes the entry point for another, concurrent thread of execution within your program.
        // This thread will end when run() returns
        @Override
        public void run() {
            try {
                for(int n = 50; n > 0; n--) {
                    System.out.println(Thread.currentThread().getName() + " : " + n);
                    Thread.sleep(500);
                }
            } catch(InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " thread interrupted");
            }
        }
    }

    //Onenote: Achieving synchronization
    //Onenote: Synchronization and System.out.println - Stack Overflow
}
