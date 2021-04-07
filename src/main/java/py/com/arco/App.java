package py.com.arco;

import com.pi4j.io.gpio.*;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.*;
import com.pusher.client.util.HttpAuthorizer;
import py.com.arco.Entity.AuthUser;
import py.com.arco.Entity.User;
import py.com.arco.Services.AuthMaquinaImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class App {
    private static GpioPinDigitalOutput pin;
    private static User userMaquina;
    private static AuthMaquinaImpl authMaquina;
    private static final Integer TIME_SHOOT_MACHINE_RX_IN_SECONDS = 40*1000;
    public static void main(String[] args){
            if(pin == null){
                GpioController gpioController = GpioFactory.getInstance();
                pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02,"led", PinState.LOW);
            }
            authMaquina = new AuthMaquinaImpl();
            Call<AuthUser> callSyncLogin = authMaquina.loginMaquinaSucursal(8);
            try{
                AuthUser authUser = callSyncLogin.execute().body();
                if(authUser != null){
                    System.out.println("Se obtuvo el usuario de la maquina");
                    Call<User> callSyncUser = authMaquina.getUser(authUser);
                    userMaquina = callSyncUser.execute().body();
                    if(userMaquina != null){
                        System.out.println("Se comienza a conectar al canal de presencia");
                        connectToPresenceChannel(authUser);
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            PusherOptions pusherOptions = new PusherOptions();
            pusherOptions.setCluster("us2");
            Pusher pusher = new Pusher("eaec0efbd968f46ba3f8", pusherOptions);
            pusher.connect();
            Channel channel = pusher.subscribe("sucursales.8");
            channel.bind("App\\Events\\HabilitarMaquinaSucursal", new SubscriptionEventListener() {
                @Override
                public void onEvent(PusherEvent event) {
                    pin.high();
                    //System.out.println(event.getData());
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
    private static void connectToPresenceChannel(AuthUser authUser){
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization",authUser.getToken_type().concat(" ").concat(authUser.getAccess_token()));
        HttpAuthorizer authorizer = new HttpAuthorizer("https://sys.arco.com.py/broadcasting/auth");
        authorizer.setHeaders(headers);
        PusherOptions pusherOptions = new PusherOptions();
        pusherOptions.setCluster("us2");
        pusherOptions.setAuthorizer(authorizer);
        Pusher pusher = new Pusher("eaec0efbd968f46ba3f8",pusherOptions);
        pusher.connect();
        PresenceChannel presenceChannel = pusher.subscribePresence("presence-dispositivos.8");
    }
    private static void detenerMaquina(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Se esta ejecutando el segundo plano");
                    Thread.sleep(TIME_SHOOT_MACHINE_RX_IN_SECONDS);
                    System.out.println("La maquina se desactivo");
                    pin.low();
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
