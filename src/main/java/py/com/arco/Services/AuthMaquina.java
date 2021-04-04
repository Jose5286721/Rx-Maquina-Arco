package py.com.arco.Services;

import py.com.arco.Entity.AuthUser;
import py.com.arco.Entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AuthMaquina {
    @GET("login/{sucursalId}")
    Call<AuthUser> loginMaquinaRx(@Path("sucursalId") int sucursalId);

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization);
}
