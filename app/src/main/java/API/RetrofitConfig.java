package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        //inform Webservice base URL
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://economia.awesomeapi.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public CoinService getCoinService() {
        return this.retrofit.create(CoinService.class);
    }

}
