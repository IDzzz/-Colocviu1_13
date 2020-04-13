package ro.pub.cs.systems.eim.Colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviul_13Service extends Service {
    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String cardinals_text = intent.getStringExtra(Constants.CARDINALS_TEXT);
        if (processingThread == null) {
            processingThread = new ProcessingThread(this, cardinals_text);
            processingThread.start();
        } else {
            processingThread.setCardinalsText(cardinals_text);
        }
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
