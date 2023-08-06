package jp.developer.bbee.myapplication.sales;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import jp.developer.bbee.myapplication.R;
import jp.developer.bbee.myapplication.sales.shop.Shop;

public class SalesShopDialog extends DialogFragment {
    private RecyclerView rv;
    private SalesAdapter salesAdapter;
    private List<Shop> shops = new ArrayList<>();
    private int position = -1;

    public static SalesShopDialog create(List<Shop> shops) {
        SalesShopDialog dialog = new SalesShopDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("shops", (ArrayList<Shop>) shops);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        shops = getArguments().getParcelableArrayList("shops");
        setCancelable(false);
        return inflater.inflate(
                R.layout.sales_shop_dialog,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("MyTag", ((Shop) getArguments().getParcelableArrayList("shops").get(0)).getName());
        initRecyclerView(view);

        view.findViewById(R.id.btn).setOnClickListener(v -> {
            if (position == -1) {
                getParentFragmentManager().setFragmentResult("not_selected", new Bundle());
            } else {
                Bundle bundle = new Bundle();
                bundle.putParcelable("shop", shops.get(position));
                getParentFragmentManager().setFragmentResult("shop", bundle);
            }
            dismiss();
        });
    }

    private void initRecyclerView(View view) {
        salesAdapter = new SalesAdapter();
        salesAdapter.setShop(shops);
        salesAdapter.setOnClickListener(getTextViewClickListener());
        rv = view.findViewById(R.id.rv);
        rv.setAdapter(salesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        salesAdapter.notifyDataSetChanged();
    }

    private Consumer<Integer> getTextViewClickListener() {
        return (Integer newPosition) -> {
            this.position = newPosition;
        };
    }
}
