package Multithread;

public class Multithread {
    static final int SIZE = 10000000;
    static final int HALFSIZE = SIZE / 2;

    private static float[] createArr(int size){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        return arr;
    }

    private static void updateValues(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void multiThreadMethod() throws InterruptedException {
        float[] arr = createArr(SIZE);
        float[] arr1 = createArr(HALFSIZE);
        float[] arr2 = createArr(HALFSIZE);
        long timeStart = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, HALFSIZE);
        System.arraycopy(arr, HALFSIZE, arr2, 0, HALFSIZE);
        Thread t1 = new Thread(() -> updateValues(arr1));
        Thread t2 = new Thread(() -> updateValues(arr2));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.arraycopy(arr1, 0, arr, 0, HALFSIZE);
        System.arraycopy(arr2, 0, arr, HALFSIZE, HALFSIZE);
        System.out.println("MultiThread lead time: " + (System.currentTimeMillis() - timeStart));
    }

    private static void singleThreadMethod(){
        float[] arr = createArr(SIZE);
        long timeStart = System.currentTimeMillis();
        updateValues(arr);
        System.out.println("SingleThread lead time: " + (System.currentTimeMillis() - timeStart));
    }

    public static void main(String[] args) {
        singleThreadMethod();
        try {
            multiThreadMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
