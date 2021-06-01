package com.hk210.spacexcrew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hk210.spacexcrew.Model.Crew;
import com.hk210.spacexcrew.R;
import com.hk210.spacexcrew.ViewModel.CrewViewModel;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {
    private Context context;
    private List<Crew> list;

    public CrewAdapter(Context context, List<Crew> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        Crew crew = list.get(position);
        holder.full.setText(crew.getName());
        holder.stat.setText(crew.getStatus());
        holder.agency.setText(crew.getAgency());
        holder.web.setText(crew.getWiki());
        holder.id.setText(crew.getId());
        Glide.with(context).load(crew.getImage()).into(holder.imageView);

    }

    public void getALlData(List<Crew> list){
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CrewViewHolder extends RecyclerView.ViewHolder{

        public TextView full,stat,agency,web,id;
        public ImageView imageView;

        public CrewViewHolder(@NonNull View itemView) {
            super(itemView);
            full = itemView.findViewById(R.id.crew_name);
            agency = itemView.findViewById(R.id.crew_agency);
            stat = itemView.findViewById(R.id.crew_status);
            web = itemView.findViewById(R.id.crew_link);
            imageView = itemView.findViewById(R.id.crew_image);
            id = itemView.findViewById(R.id.crew_id);
        }
    }


}
