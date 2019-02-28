package andreyskakunenko.footballinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import andreyskakunenko.footballinfo.Adapters.StandingsAdapter;
import andreyskakunenko.footballinfo.Model.Standings;
import andreyskakunenko.footballinfo.Retrofit.Common;
import andreyskakunenko.footballinfo.Retrofit.MyAPI;
import andreyskakunenko.footballinfo.Retrofit.RetrofitClient;
import andreyskakunenko.footballinfo.data.AppRoomDatabase;
import andreyskakunenko.footballinfo.data.StandingsDao;
import andreyskakunenko.footballinfo.data.StandingsEntity;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CompetitionActivity extends AppCompatActivity {

    TextView areaName, leagueName, startDate, endDate, currentMatchDay;
    ImageView emblem;
    RecyclerView mRecyclerView;
    CompositeDisposable mCompositeDisposable;
    MyAPI mAPI;
    int id, cmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        areaName = findViewById(R.id.area_name);
        leagueName = findViewById(R.id.league_name);
        startDate = findViewById(R.id.start_date);
        endDate = findViewById(R.id.end_date);
        currentMatchDay = findViewById(R.id.current_match_day);
        emblem = findViewById(R.id.emblem_id);
        mRecyclerView = findViewById(R.id.standings_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        if ((getIntent() != null)) {
            areaName.setText(getIntent().getStringExtra("areaName"));
            id = getIntent().getIntExtra("competitionId", 110);
            leagueName.setText(getIntent().getStringExtra("competitionName"));
            startDate.setText(getIntent().getStringExtra("competitionStartDate"));
            endDate.setText(getIntent().getStringExtra("competitionEndDate"));
            String pathLogo = "file:///android_asset/i" + id + ".png";

            Picasso.get()
                    .load(pathLogo)
                    .placeholder(R.drawable.fire_ball)
                    .into(emblem);
            ;

        }

        if (Common.isNetworkAvailable(this)) {
            mCompositeDisposable = new CompositeDisposable();
            Retrofit retrofit = RetrofitClient.getInstance();
            mAPI = retrofit.create(MyAPI.class);

            fetchData();
        }else {
            dataBaseFrom();
            Toast.makeText(this,"No internet connection",Toast.LENGTH_LONG).show();
        }
    }

    private void dataBaseTo(Standings standings){
        List<StandingsEntity> list= new ArrayList<>();
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        StandingsDao dao = db.mStandingsDao();
        int count = standings.getStandings().get(0).getTable().size();
        for (int i=0; i<count;i++){

            final StandingsEntity se = new StandingsEntity();
            se.teamId = standings.getStandings().get(0).getTable().get(i).getTeam().getId();
            se.currentMatchDay = standings.getSeason().getCurrentMatchday();
            se.teamPos=standings.getStandings().get(0).getTable().get(i).getPosition();
            se.teamName=standings.getStandings().get(0).getTable().get(i).getTeam().getName();
            se.pg=standings.getStandings().get(0).getTable().get(i).getPlayedGames();
            se.w=standings.getStandings().get(0).getTable().get(i).getWon();
            se.d=standings.getStandings().get(0).getTable().get(i).getDraw();
            se.l=standings.getStandings().get(0).getTable().get(i).getLost();
            se.f=standings.getStandings().get(0).getTable().get(i).getGoalsFor();
            se.a=standings.getStandings().get(0).getTable().get(i).getGoalsAgainst();
            se.gd=standings.getStandings().get(0).getTable().get(i).getGoalDifference();
            se.pts=standings.getStandings().get(0).getTable().get(i).getPoints();

            list.add(se);

            Completable.fromAction(new Action() {
                @Override
                    public void run() throws Exception {
                        dao.insert(se);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });



        }
        displayData(list);
    }


    private void dataBaseFrom() {
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        db.mStandingsDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(standingsEntities -> {
                    List<StandingsEntity> standings = standingsEntities;
                    displayData(standings);
                });

    }

    private void fetchData() {
        String url = id + "/standings";
        mCompositeDisposable.add(mAPI.getProfile(Common.getAPIUrl(url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Standings>() {
                    @Override
                    public void accept(Standings standings) throws Exception {
                        cmd = standings.getSeason().getCurrentMatchday();
                        dataBaseTo(standings);
                    }

                })
        );
    }

    private void displayData(List<StandingsEntity> standings) {
        currentMatchDay.setText(cmd + " d");
        StandingsAdapter adapter = new StandingsAdapter(this, standings);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
    }
}