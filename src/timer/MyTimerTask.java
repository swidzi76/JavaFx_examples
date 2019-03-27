package timer;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    private ControllerTimer controllerTimer;
    public void setControllerTimer(ControllerTimer controllerTimer){
        this.controllerTimer = controllerTimer;
    }
    @Override
    public void run() {
//       C
//       controllerTimer.buttonClicked();
        // controllerTimer.label1.setText("abc");
        System.out.println(" timer działa");
    }

}
