package py.com.arco;


import com.pi4j.io.gpio.*;

public class App {
    private static GpioPinDigitalOutput pin;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hola a todos");
        if(pin==null){
            GpioController gpioController = GpioFactory.getInstance();
            pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05,"LED", PinState.LOW);
        }
        pin.toggle();
    }
}
