package com.example.presentator.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presentator.R;
import com.example.presentator.model.entities.News;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList = new LinkedList<>();

    public void addItem(News news) {
        insertInSorted(news);
        notifyDataSetChanged();
    }

    public void insertInSorted(News news) {
        if (newsList.size() == 0) {
            newsList.add(news);
            return;
        }
        int i = 0;
        for (News n : newsList) {
            if (n.getGift().getCreationDate() < news.getGift().getCreationDate()) {
                newsList.add(i, news);
                return;
            }
            ++i;
        }
        newsList.add(news);
    }

    public void updateItem(News news) {
        int index = newsList.indexOf(news);
        newsList.set(index, news);
        notifyItemChanged(index);
    }

    public void setItems(Collection<News> newsCollection) {
        newsList.addAll(newsCollection);
        notifyDataSetChanged();
    }

    public void clearItems() {
        newsList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_news_item, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImageView;
        private TextView nickTextView;
        private TextView creationDateTextView;
        private TextView presentNameTextView;
        private ImageView presentImageView;
        private TextView moneyCollectedTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.profile_image_view);
            nickTextView = itemView.findViewById(R.id.author_nick_text_view);
            creationDateTextView = itemView.findViewById(R.id.creation_date_text_view);
            presentNameTextView = itemView.findViewById(R.id.present_name_text_view);
            presentImageView = itemView.findViewById(R.id.present_image_view);
            moneyCollectedTextView = itemView.findViewById(R.id.money_collected_text_view);
        }

        public void bind(News news) {
            nickTextView.setText(news.getUser().getNick());
            moneyCollectedTextView.setText(news.getGift().getMoneyCollected());
            presentNameTextView.setText(news.getGift().getPresentName());
            creationDateTextView.setText(news.getGift().stringCreatedDate());
            Picasso.with(itemView.getContext()).load(news.getUser().getImageURL()).into(userImageView);
            Picasso.with(itemView.getContext()).load(news.getGift().getPresentImageURL()).into(presentImageView);
        }
    }
}