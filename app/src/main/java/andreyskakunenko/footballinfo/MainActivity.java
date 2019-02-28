package andreyskakunenko.footballinfo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

import andreyskakunenko.footballinfo.Adapters.CompetitionsAdapter;
import andreyskakunenko.footballinfo.Model.Competitions;
import andreyskakunenko.footballinfo.Retrofit.Common;
import andreyskakunenko.footballinfo.Retrofit.MyAPI;
import andreyskakunenko.footballinfo.Retrofit.RetrofitClient;
import andreyskakunenko.footballinfo.data.AppRoomDatabase;
import andreyskakunenko.footballinfo.data.CompetitionEntity;
import andreyskakunenko.footballinfo.data.CompetitionsDao;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CompositeDisposable mCompositeDisposable;
    MyAPI mAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Request Internet permission;
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();




        mRecyclerView = findViewById(R.id.competitions_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    private void fetchData() {
        mCompositeDisposable.add(mAPI.getResults(Common.BASE_URL+"competitions/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Competitions>() {
                    @Override
                    public void accept(Competitions competitions) throws Exception {
                        dataBaseTo(competitions);

                    }
                })
        );
    }

    private void displayData(List<CompetitionEntity> results) {
        CompetitionsAdapter adapter = new CompetitionsAdapter(this,results);
        mRecyclerView.setAdapter(adapter);
    }


    private void dataBaseTo(Competitions competitions){
        List<CompetitionEntity> list= new ArrayList<>();
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        CompetitionsDao dao = db.mCompetitionsDao();
        for (int i=0; i<competitions.getCompetitions().size();i++){

            final CompetitionEntity ce = new CompetitionEntity();
            ce.seasonId = competitions.getCompetitions().get(i).getId();
            ce.seasonArea = competitions.getCompetitions().get(i).getArea().getName();
            ce.seasonName = competitions.getCompetitions().get(i).getName();
            if (isContains( ce.seasonId)){
                ce.startDate = competitions.getCompetitions().get(i).getCurrentSeason().getStartDate();
                ce.endDate = competitions.getCompetitions().get(i).getCurrentSeason().getEndDate();
            }
            list.add(ce);




            if (true)//dao.getById(competitions.getCompetitions().get(i).getId())==null)
            {
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        dao.insert(ce);
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
            else{
                Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        dao.update(ce);
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
        }
        displayData(list);
    }

    private void dataBaseFrom(){
        AppRoomDatabase db = AppRoomDatabase.getInstance(this);
        db.mCompetitionsDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(competitionEntities -> {
                    List<CompetitionEntity> competitions = competitionEntities;
                    displayData(competitions);
                });

    }

    public static boolean isContains(int id) {
        int freeCompetition[] = {2000, 2001, 2002, 2003, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2021};
        for (int j = 0; j < freeCompetition.length; j++) {
            if (freeCompetition[j] == id)
                return true;
        }
        return false;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mCompositeDisposable!=null)mCompositeDisposable.clear();
    }

}

