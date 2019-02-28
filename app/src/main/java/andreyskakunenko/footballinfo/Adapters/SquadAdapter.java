package andreyskakunenko.footballinfo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import andreyskakunenko.footballinfo.R;
import andreyskakunenko.footballinfo.data.TeamEntity;

class SquadHolder extends RecyclerView.ViewHolder{

    TextView role,name,position,dataBirth,countryBirth,nationality;
    CardView cardSquad;

    public SquadHolder(@NonNull View itemView) {
        super(itemView);
        role = itemView.findViewById(R.id.role);
        name = itemView.findViewById(R.id.player_name_value);
        position = itemView.findViewById(R.id.player_pos_value);
        dataBirth = itemView.findViewById(R.id.player_birth_value);
        countryBirth = itemView.findViewById(R.id.player_country_birth);
        nationality = itemView.findViewById(R.id.player_nationality_value);
        cardSquad = itemView.findViewById(R.id.card_squad);

    }
}

public class SquadAdapter extends RecyclerView.Adapter<SquadHolder>{

    List<TeamEntity> mTeam;
    Context mContext;

    public SquadAdapter(List<TeamEntity> team, Context context) {
        mTeam = team;
        mContext = context;
    }

    @NonNull
    @Override
    public SquadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.squad_item,viewGroup,false);
        return new SquadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquadHolder squadHolder, int i) {

        squadHolder.role.setText(mTeam.get(i).playerRole);
        squadHolder.name.setText(mTeam.get(i).playerName);
        squadHolder.position.setText(mTeam.get(i).playerPosition);
        squadHolder.dataBirth.setText(dateEdit(mTeam.get(i).playerDateBirth));
        squadHolder.countryBirth.setText(mTeam.get(i).playerCountryBirth);
        squadHolder.nationality.setText(mTeam.get(i).playerNationality);

        if (i%2==0){
            squadHolder.cardSquad.setCardBackgroundColor(mContext.getResources().getColor(R.color.backgroundColorGrayLite));
        }else{
            squadHolder.cardSquad.setCardBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryWhite));
        }

    }

    @Override
    public int getItemCount() {
        return mTeam.size();
    }

    public String dateEdit(String longDate){
        String newDate =longDate.split("T")[0];
        return newDate;
    }
}
