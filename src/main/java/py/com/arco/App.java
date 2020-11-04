package py.com.arco;

import com.pi4j.io.gpio.*;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.ChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;

public class App {
    private static GpioPinDigitalOutput pin;
    public static void main(String[] args){
            if(pin == null){
                GpioController gpioController = GpioFactory.getInstance();
                pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05,"led", PinState.LOW);
            }
            PusherOptions pusherOptions = new PusherOptions();
            pusherOptions.setCluster("us2");
            Pusher pusher = new Pusher("eaec0efbd968f46ba3f8", pusherOptions);
            pusher.connect();
            Channel channel = pusher.subscribe("prueba");
            channel.bind("evento", new SubscriptionEventListener() {
                @Override
                public void onEvent(PusherEvent event) {
                    pin.toggle();
                }
            });
            while (true){
                try{
                    Thread.sleep(200);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
    }
}
