package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {
    private String cardinalsText;
    private Context context;

    private boolean isRunning = false;
    private boolean receivedMessage = false;

    public ProcessingThread(Context context, String cardinalsText) {
        this.context = context;
        this.cardinalsText = cardinalsText;
        isRunning = true;
        receivedMessage = true;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            if (receivedMessage) {
                sleep();
                sendMessage();
                receivedMessage = false;
            }
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_TYPE);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + cardinalsText);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    void setCardinalsText(String cardinalsText) {
        this.cardinalsText = cardinalsText;
        this.receivedMessage = true;
    }

    void stopThread() {
        isRunning = false;
    }
}
