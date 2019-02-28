package andreyskakunenko.footballinfo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andreyskakunenko.footballinfo.Adapters.SquadAdapter;
import andreyskakunenko.footballinfo.Model.Team;
import andreyskakunenko.footballinfo.Retrofit.Common;
import andreyskakunenko.footballinfo.Retrofit.MyAPI;
import andreyskakunenko.footballinfo.Retrofit.RetrofitClient;
import andreyskakunenko.footballinfo.data.AppRoomDatabase;
import andreyskakunenko.footballinfo.data.TeamDao;
import andreyskakunenko.footballinfo.data.TeamEntity;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class TeamDetailsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    TextView country,name,email,birth;
    Button site;
    CompositeDisposable mCompositeDisposable;
    MyAPI mAPI;
    int id ;
    Team mTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        country = findViewById(R.id.team_country_value);
        name = findViewById(R.id.team_name_value);
        site = findViewById(R.id.web_site_value);
        email = findViewById(R.id.email_value);
        birth = findViewById(R.id.founded_value);
        mRecyclerView = findViewById(R.id.player_container);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        id = getIntent().getIntExtra("teamId",2019);

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

    private void dataBaseFrom() {
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        db.mTeamDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(teamEntity -> {
                    List<TeamEntity> teams = teamEntity;
                    displayData(teams);
                });
    }

    private void fetchData() {
        String url = "teams/"+id;
        mCompositeDisposable.add(mAPI.getDetail(Common.getTeamUrl(url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Team>() {
                    @Override
                    public void accept(Team team) throws Exception {
                        dataBaseTo(team);
                        //displayData(team);
                    }
                })
        );
    }

    private void displayData(List<TeamEntity> teams) {
        country.setText(teams.get(0).teamCountry);
        name.setText(teams.get(0).teamName);
        site.setText(teams.get(0).teamURL);
        site.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(teams.get(0).teamURL));
            startActivity(browserIntent);
        });
        email.setText(teams.get(0).teamEmail);
        birth.setText(teams.get(0).teamFounded+" yy");
        SquadAdapter adapter = new SquadAdapter( teams,this);
        mRecyclerView.setAdapter(adapter);
    }

    private void dataBaseTo(Team team){
        List<TeamEntity> list= new ArrayList<>();
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        TeamDao dao = db.mTeamDao();
        int count = team.getSquad().size();
        for (int i=0; i<count;i++){

            final TeamEntity te = new TeamEntity();
            te.teamId = team.getId();
            te.teamCountry = team.getArea().getName();
            te.teamName = team.getName();
            te.teamURL = team.getWebsite();
            te.teamFounded = team.getFounded();
            te.playerName = team.getSquad().get(i).getName();
            te.playerPosition = team.getSquad().get(i).getPosition();
            te.playerRole = team.getSquad().get(i).getRole();
            te.playerDateBirth = team.getSquad().get(i).getDateOfBirth();
            te.playerCountryBirth = team.getSquad().get(i).getCountryOfBirth();
            te.playerNationality = team.getSquad().get(i).getNationality();

            list.add(te);

            Completable.fromAction(new Action() {
                @Override
                public void run() throws Exception {
                    dao.insert(te);
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


    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable!=null)mCompositeDisposable.clear();
    }
}
