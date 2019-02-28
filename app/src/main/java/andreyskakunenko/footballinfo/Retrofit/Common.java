package andreyskakunenko.footballinfo.Retrofit;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class Common {
    private Context mContext;
    public static final String BASE_URL = "http://api.football-data.org/v2/";
    public static final String X_Auth_Token = "e955179bb298492dbda0626a8c1f488a";

    private Common(@NonNull Context context){
        mContext = context.getApplicationContext();
    }


    public static String getAPIUrl(String source)
    {
        return BASE_URL+"competitions/"+source;
    }
    public static String getTeamUrl(String source){return BASE_URL+source;}


    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null && activeNetworkInfo.isConnected();
    }


}
