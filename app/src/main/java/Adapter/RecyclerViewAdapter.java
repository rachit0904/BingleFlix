package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.fragmentsampleapp.R;
import com.project.fragmentsampleapp.movieDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.MovieData;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<MovieData> list;
    public RecyclerViewAdapter(Context context,List<MovieData> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        MovieData moviesData=list.get(position);
        holder.name.setText(moviesData.getTitle());
        holder.year.setText(moviesData.getYear());
        holder.type.setText(moviesData.getType());
        String posterUrl=moviesData.getPoster();
        Picasso.
                with(context)
                .load(posterUrl)
                .noFade()
                .fit()
                .placeholder(R.drawable.movie_icon) // can also be a drawable
                .into(holder.Poster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Poster;
        TextView name,year,type;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            Poster=(ImageView) itemView.findViewById(R.id.moviePoster);
            name=(TextView) itemView.findViewById(R.id.movieName);
            year=(TextView) itemView.findViewById(R.id.movieDur);
            type=(TextView) itemView.findViewById(R.id.movieRating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, movieDetails.class);
                    MovieData data=list.get(getAdapterPosition());
                    intent.putExtra("imdbId",data.getImdbId());
                    intent.putExtra("choice","moviedata");
                    context.startActivity(intent);
                }
            });
        }
    }
}
