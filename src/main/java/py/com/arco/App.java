package py.com.arco;

import com.pi4j.io.gpio.*;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.*;

public class App {
    private static GpioPinDigitalOutput pin;
    private static final Integer TIME_SHOOT_MACHINE_RX_IN_SECONDS = 40*1000;
    public static void main(String[] args){
            if(pin == null){
                GpioController gpioController = GpioFactory.getInstance();
                pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02,"led", PinState.LOW);
            }
            PusherOptions pusherOptions = new PusherOptions();
            pusherOptions.setCluster("us2");
            Pusher pusher = new Pusher("eaec0efbd968f46ba3f8", pusherOptions);
            pusher.connect();
            Channel channel = pusher.subscribe("sucursales.2");
            channel.bind("App\\Events\\HabilitarMaquinaSucursal", new SubscriptionEventListener() {
                @Override
                public void onEvent(PusherEvent event) {
                    pin.high();
                    //System.out.println(event.getData());
                    detenerMaquina();
                    detenerMaquina();
                }
            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

    }
    private static void detenerMaquina(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(TIME_SHOOT_MACHINE_RX_IN_SECONDS);
                    System.out.println("Lmaquina se desaactivo");
                    pin.low();
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
