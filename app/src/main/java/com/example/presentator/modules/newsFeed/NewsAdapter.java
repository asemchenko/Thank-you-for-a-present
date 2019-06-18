package com.example.presentator.modules.newsFeed;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presentator.R;
import com.example.presentator.model.entities.News;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private boolean hideGiftButton = false;
    private List<News> newsList = new LinkedList<>();

    public NewsAdapter(boolean hideGiftButton) {
        this.hideGiftButton = hideGiftButton;
    }

    public NewsAdapter() {
    }

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
        private TextView descriptionTextView;
        private Button giftThisButton;

        public NewsViewHolder(View itemView) {
            super(itemView);
            userImageView = itemView.findViewById(R.id.profile_image_view);
            nickTextView = itemView.findViewById(R.id.author_nick_text_view);
            creationDateTextView = itemView.findViewById(R.id.creation_date_text_view);
            presentNameTextView = itemView.findViewById(R.id.present_name_text_view);
            presentImageView = itemView.findViewById(R.id.present_image_view);
            descriptionTextView = itemView.findViewById(R.id.news_item_tw_description);
            giftThisButton = itemView.findViewById(R.id.news_item_btn_gift);
        }

        private void prepareGiftButton(News news) {
            if (hideGiftButton) {
                giftThisButton.setVisibility(View.INVISIBLE);
            } else {
                giftThisButton.setOnClickListener(view -> {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    news.getGift().setGiftedByUserUid(uid);
                    FirebaseDatabase.getInstance().getReference().child("gifts").child(news.getUserId()).child(news.getGiftId()).setValue(news.getGift());
                });
            }
        }

        public void bind(News news) {
            nickTextView.setText(news.getUser().getName());
            descriptionTextView.setText(news.getGift().getDescription());
            presentNameTextView.setText(news.getGift().getPresentName());
            creationDateTextView.setText(news.getGift().stringCreatedDate());
            Picasso.with(itemView.getContext()).load(news.getUser().getImageURL()).into(userImageView);
            Picasso.with(itemView.getContext()).load(news.getGift().getPresentImageURL()).into(presentImageView);
            if (news.getGift().getGiftedByUserUid() != null) {
                disableGiftButton();
            } else {
                enableGiftButton();
            }
            prepareGiftButton(news);
        }

        private void disableGiftButton() {
            giftThisButton.setText("Already gifted");
            giftThisButton.setEnabled(false);
            giftThisButton.setBackgroundResource(R.drawable.button_rounded_disabled);

        }

        private void enableGiftButton() {
            giftThisButton.setText("Gift this");
            giftThisButton.setBackgroundColor(Color.parseColor("#90B140"));
            giftThisButton.setEnabled(true);
            giftThisButton.setBackgroundResource(R.drawable.button_rounded);
        }
    }
}