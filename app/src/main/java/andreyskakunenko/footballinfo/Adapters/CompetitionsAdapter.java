package andreyskakunenko.footballinfo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

import andreyskakunenko.footballinfo.CompetitionActivity;
import andreyskakunenko.footballinfo.ErrorActivity;
import andreyskakunenko.footballinfo.Interface.ItemClickListener;
import andreyskakunenko.footballinfo.MainActivity;
import andreyskakunenko.footballinfo.R;
import andreyskakunenko.footballinfo.data.CompetitionEntity;

class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView countryName, competitionName,startDayValue, endDayValue,status;
    ImageView logo;
    private ItemClickListener itemClickListener;
    CardView mCardView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        countryName = itemView.findViewById(R.id.country_name);
        competitionName = itemView.findViewById(R.id.competition_name);
        startDayValue = itemView.findViewById(R.id.start_day_value);
        endDayValue=itemView.findViewById(R.id.date_end_value);
        status=itemView.findViewById(R.id.status_value);
        mCardView = itemView.findViewById(R.id.card_item_competition);
        logo = itemView.findViewById(R.id.image_main_list);
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

public class CompetitionsAdapter extends RecyclerView.Adapter<MyHolder>{

    private Context mContext;
    private List<CompetitionEntity>  mCompetitions;

    public CompetitionsAdapter(Context context, List<CompetitionEntity> competitions) {
        mContext = context;
        mCompetitions = competitions;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.competitions_item,viewGroup,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {

        int id = mCompetitions.get(i).seasonId;
        String endDate = "2020.02.02";
        String startDate = "2019.01.01";

        myHolder.countryName.setText(mCompetitions.get(i).seasonArea);
        myHolder.competitionName.setText(mCompetitions.get(i).seasonName);
        myHolder.startDayValue.setText(startDate);
        myHolder.endDayValue.setText(endDate);

        if(MainActivity.isContains(id))
        {
            myHolder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorAvailableItem));
            myHolder.status.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundGreen));
            myHolder.status.setText(mContext.getResources().getString(R.string.free));
            myHolder.startDayValue.setText(mCompetitions.get(i).startDate);
            myHolder.endDayValue.setText(mCompetitions.get(i).endDate);

            String logoName = "file:///android_asset/i"+id+".png";

            Picasso.get()
                    .load(logoName)
                    .placeholder(R.drawable.fire_ball)
                    .into(myHolder.logo)
            ;

        }else
        {
            myHolder.mCardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorNoAvItem));
            myHolder.status.setBackgroundColor(mContext.getResources().getColor(R.color.backgroundRed));
            myHolder.status.setText(mContext.getResources().getString(R.string.paid));
            Picasso.get()
                    .load(R.drawable.ball_classik)
                    .into(myHolder.logo)
            ;
        }

        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                int id = mCompetitions.get(position).seasonId;
                if(MainActivity.isContains(id))
                {
                Intent partitionCompetition = new Intent(mContext, CompetitionActivity.class);
                partitionCompetition.putExtra("competitionId",id);
                partitionCompetition.putExtra("areaName",mCompetitions.get(position).seasonArea);
                partitionCompetition.putExtra("competitionName",mCompetitions.get(position).seasonName);
                partitionCompetition.putExtra("competitionStartDate", mCompetitions.get(position).startDate);
                partitionCompetition.putExtra("competitionEndDate", mCompetitions.get(position).endDate);
                partitionCompetition.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(partitionCompetition);
                }else
                    {
                        Intent byNow  = new Intent(mContext, ErrorActivity.class);
                        mContext.startActivity(byNow);
                    }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCompetitions.size();
    }


}
