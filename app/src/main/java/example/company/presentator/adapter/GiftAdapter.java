package example.company.presentator.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import example.company.presentator.Gift;
import example.company.presentator.R;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {
    class GiftViewHolder extends RecyclerView.ViewHolder {
        private TextView descriptionTextView;
        public GiftViewHolder(View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.gift_description_text_view);
        }

        public void bind(Gift gift) {
            descriptionTextView.setText(gift.getDescription());
        }

    }
    private List<Gift> gifts;

    public GiftAdapter(Collection<Gift> gifts) {
        this.gifts = new ArrayList<>(gifts);
    }

    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_view, viewGroup, false);
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftViewHolder giftViewHolder, int i) {
       giftViewHolder.bind(gifts.get(i));
    }

    @Override
    public int getItemCount() {
        return gifts.size();
    }

    public void setItems(Collection<Gift> gifts) {
        this.gifts.addAll(gifts);
        notifyDataSetChanged();
    }

    public void clearItems() {
        gifts.clear();
        notifyDataSetChanged();
    }
}
