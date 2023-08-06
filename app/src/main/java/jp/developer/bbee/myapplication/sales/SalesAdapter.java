package jp.developer.bbee.myapplication.sales;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import jp.developer.bbee.myapplication.R;
import jp.developer.bbee.myapplication.sales.shop.Shop;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.SalesHolder> {
    private List<Shop> shops = new ArrayList<>();
    private int selectedPosition = -1;
    private Consumer<Integer> onClickListener;

    public void setShop(List<Shop> shops) {
        this.shops = shops;
    }

    @NonNull
    @Override
    public SalesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shop_list_item, parent, false);
        return new SalesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHolder holder, int position) {
        final int newPosition = position;
        holder.bind(shops.get(position), newPosition, selectedPosition);
        holder.itemView.setOnClickListener(v -> {
            final int oldPosition = selectedPosition;
            onClickListener.accept(newPosition);
            selectedPosition = newPosition;
            notifyItemChanged(newPosition);
            if (oldPosition != -1) notifyItemChanged(oldPosition);
        });
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public void setOnClickListener(Consumer<Integer> listener) {
        onClickListener = listener;
    }

    static class SalesHolder extends RecyclerView.ViewHolder {
        TextView tvShopName = itemView.findViewById(R.id.tvName);

        private SalesHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Shop shop, int position, int selectedPosition) {
            tvShopName.setText(shop.getName());
            if (selectedPosition == position) {
                itemView.setBackgroundColor(itemView.getResources().getColor(
                        R.color.selected_back_ground_color, null));
            } else {
                itemView.setBackgroundColor(itemView.getResources().getColor(
                        R.color.default_back_ground_color, null));
            }
        }
    }
}
