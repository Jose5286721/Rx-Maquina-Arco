package py.com.arco.Services;

import py.com.arco.Entity.AuthUser;
import py.com.arco.Entity.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthMaquinaImpl {
    Retrofit retrofit;
    public AuthMaquinaImpl(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://sys.arco.com.py/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<AuthUser> loginMaquinaSucursal(int sucursalId){
        AuthMaquina authMaquina = retrofit.create(AuthMaquina.class);
        return authMaquina.loginMaquinaRx(sucursalId);
    }

    public Call<User> getUser(AuthUser authUser){
        AuthMaquina authMaquina = retrofit.create(AuthMaquina.class);
        String autorization = authUser.getToken_type().concat(" ").concat(authUser.getAccess_token());
        return authMaquina.getUser(autorization);
    }

}
