package com.example.presentator.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presentator.R;
import com.example.presentator.model.entities.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>  {
    private List<User> peopleList = new ArrayList<>();

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImageView;
        private TextView nickTextView;
        private TextView nameTextView;
        private TextView genderTextView;


        public PersonViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.profile_image_view);
            nickTextView = itemView.findViewById(R.id.nick_text_view);
            nameTextView =itemView.findViewById(R.id.name_text_view);
            genderTextView =itemView.findViewById(R.id.gender_text_view);
        }

        public void bind(User user) {
            nickTextView.setText(user.getNick());
            nameTextView.setText(user.getName());
            genderTextView.setText(user.getGender().toString());
            Picasso.with(itemView.getContext()).load(user.getImageURL()).into(userImageView);

        }
    }


    public void setItems(Collection<User> peopleCollection) {
        peopleList.addAll(peopleCollection);
        notifyDataSetChanged();
    }

    public void clearItems() {
        peopleList.clear();
        notifyDataSetChanged();
    }

    @NonNull

    public PersonAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_person_item, viewGroup, false);
        return new PersonAdapter.PersonViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PersonAdapter.PersonViewHolder holder, int position) {
        holder.bind(peopleList.get(position));
    }


    public int getItemCount() {
        return peopleList.size();
    }
}
