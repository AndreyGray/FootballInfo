package andreyskakunenko.footballinfo.Retrofit;

import andreyskakunenko.footballinfo.Model.Competitions;
import andreyskakunenko.footballinfo.Model.Standings;
import andreyskakunenko.footballinfo.Model.Team;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface MyAPI {


    @Headers("X-Auth-Token: e955179bb298492dbda0626a8c1f488a")
    @GET
    Observable<Competitions> getResults(@Url String url);

    @Headers("X-Auth-Token: e955179bb298492dbda0626a8c1f488a")
    @GET
    Observable<Standings> getProfile(@Url String url);

    @Headers("X-Auth-Token: e955179bb298492dbda0626a8c1f488a")
    @GET
    Observable<Team> getDetail(@Url String url);
}
