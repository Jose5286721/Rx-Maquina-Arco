package py.com.arco;

import com.pi4j.io.gpio.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hola a todos");
        GpioController gpioController = GpioFactory.getInstance();
        GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01,"LED",PinState.HIGH);
        pin.setShutdownOptions(true,PinState.LOW);
        while(true){
            Thread.sleep(100);
            pin.low();
            Thread.sleep(100);
            pin.high();
        }
    }
}
