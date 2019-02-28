package andreyskakunenko.footballinfo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import andreyskakunenko.footballinfo.Interface.ItemClickListener;
import andreyskakunenko.footballinfo.R;
import andreyskakunenko.footballinfo.TeamDetailsActivity;
import andreyskakunenko.footballinfo.data.StandingsEntity;

class StandingsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView positionN,teamName,playedGames,won,draw,lost,goalsFor,goalsAgainst, goalsDifference,points;
    CardView cardStandings;
    private ItemClickListener itemClickListener;

    public StandingsHolder(@NonNull View itemView) {
        super(itemView);
        cardStandings=itemView.findViewById(R.id.card_standings);
        positionN=itemView.findViewById(R.id.team_numbers);
        teamName=itemView.findViewById(R.id.team_name);
        playedGames=itemView.findViewById(R.id.played_games);
        won=itemView.findViewById(R.id.games_won);
        draw=itemView.findViewById(R.id.games_draw);
        lost=itemView.findViewById(R.id.games_lost);
        goalsFor=itemView.findViewById(R.id.goals_for);
        goalsAgainst=itemView.findViewById(R.id.goals_against);
        goalsDifference=itemView.findViewById(R.id.goals_difference);
        points=itemView.findViewById(R.id.points);
        itemView.setOnClickListener(this);


    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());

    }
}

public class StandingsAdapter extends RecyclerView.Adapter<StandingsHolder> {

    private Context mContext;
    private List<StandingsEntity> mStandings;

    public StandingsAdapter(Context context, List<StandingsEntity> standing) {
        mContext = context;
        mStandings = standing;
    }

    @NonNull
    @Override
    public StandingsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.standings_item, viewGroup, false);
        return new StandingsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsHolder standingsHolder, int i) {
        standingsHolder.positionN.setText(String.valueOf(mStandings.get(i).teamPos));
        standingsHolder.teamName.setText(mStandings.get(i).teamName);
        standingsHolder.playedGames.setText(String.valueOf(mStandings.get(i).pg));
        standingsHolder.won.setText(String.valueOf(mStandings.get(i).w));
        standingsHolder.draw.setText(String.valueOf(mStandings.get(i).d));
        standingsHolder.lost.setText(String.valueOf(mStandings.get(i).l));
        standingsHolder.goalsFor.setText(String.valueOf(mStandings.get(i).f));
        standingsHolder.goalsAgainst.setText(String.valueOf(mStandings.get(i).a));
        standingsHolder.goalsDifference.setText(String.valueOf(mStandings.get(i).gd));
        standingsHolder.points.setText(String.valueOf(mStandings.get(i).pts));

        if (i % 2 == 0) {
            standingsHolder.cardStandings.setCardBackgroundColor(mContext.getResources().getColor(R.color.backgroundColorGrayLite));
        } else {
            standingsHolder.cardStandings.setCardBackgroundColor(mContext.getResources().getColor(R.color.backgroundColorGray));
        }


        standingsHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int teamId = mStandings.get(position).teamId;
                Intent teamDetail = new Intent(mContext, TeamDetailsActivity.class);
                teamDetail.putExtra("teamId", teamId);
                teamDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(teamDetail);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mStandings.size();
    }
}