import java.util.Random;

class Observer{
    public void update(int waterLevel){}
}

class Alarm extends Observer{
    String alarmId;

    Alarm(String alarmId){
        this.alarmId = alarmId;
    }

    public void update(int waterLevel){
        System.out.println("Alarm " + alarmId + " : " + (waterLevel >= 50 ? "ON" : "OFF"));
    }
}

class Display extends Observer{
    public void update(int waterLevel){
        System.out.println("Water Level : " + waterLevel);
    }
}

class SMSManager extends Observer{
    public void update(int waterLevel){
        System.out.println("Sending SMS... " + waterLevel);
    }
}

class ControlPanel{
    private Alarm alarm;
    private Display display;
    private SMSManager smsManager;

    private int waterLevel;

    public void putAlarm(Alarm alarm){
        this.alarm = alarm;
    }

    public void putDisplay(Display display){
        this.display = display;
    }

    public void putSMSManager(SMSManager smsManager){
        this.smsManager = smsManager;
    }

    public void notifyObjects(){
        alarm.update(waterLevel);
        display.update(waterLevel);
        smsManager.update( waterLevel);
    }

    public void setData(int waterLevel){
        if(this.waterLevel != waterLevel){
            this.waterLevel = waterLevel;
            notifyObjects();
        }
    }
}

class Demo{
    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.putAlarm(new Alarm("Alarm 1"));
        controlPanel.putDisplay(new Display());
        controlPanel.putSMSManager(new SMSManager());

        Random random = new Random();
        while (true){
            controlPanel.setData(random.nextInt(101));
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }

            System.out.println("==================================");
        }
    }
}