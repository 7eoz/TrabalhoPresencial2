package API;

import Model.Coin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoinService {

    @GET("{cond}")
    Call<Coin> getCoin(@Path("cond") String cond);
}

