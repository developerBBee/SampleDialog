package jp.developer.bbee.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import jp.developer.bbee.myapplication.sales.SalesShopDialog;
import jp.developer.bbee.myapplication.sales.shop.Shop;

public class MainActivity extends AppCompatActivity {
    private List<Shop> shops = new ArrayList<>();
    private MainViewModel viewModel;
    private TextView shopId;
    private TextView shopName;
    private TextView shopAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        shopId = findViewById(R.id.shopId);
        shopName = findViewById(R.id.shopName);
        shopAddress = findViewById(R.id.shopAddress);

        viewModel.shop.observe(this, shop -> {
            shopId.setText(shop.getId());
            shopName.setText(shop.getName());
            shopAddress.setText(shop.getAddress());
        });

        initShops();
        FragmentManager manager = getSupportFragmentManager();
        FragmentResultListener listener = (key, result) -> {
            if (key.equals("shop")) {
                Shop shop = result.getParcelable("shop");
                Toast.makeText(this, shop.getAddress(), Toast.LENGTH_SHORT).show();
                viewModel.setShop(shop);
            }
            viewModel.dismissedSalesShopDialog();
        };

        if (viewModel.getSalesShopDialog() == null) {
            viewModel.setSalesShopDialog(SalesShopDialog.create(shops));
            viewModel.getSalesShopDialog().show(manager, "dialog");
        }
        manager.setFragmentResultListener(
                "shop",
                this,
                listener
        );
        manager.setFragmentResultListener(
                "not_selected",
                this,
                listener
        );
    }

    private void initShops() {
        shops.add(new Shop("1", "東京本店", "東京都港区"));
        shops.add(new Shop("2", "大阪販売店", "大阪府大阪市"));
        shops.add(new Shop("3", "名古屋販売店", "愛知県名古屋市"));
        shops.add(new Shop("4", "福岡販売店", "福岡県福岡市"));
        shops.add(new Shop("5", "札幌販売店", "北海道札幌市"));
        shops.add(new Shop("6", "仙台販売店", "宮城県仙台市"));
        shops.add(new Shop("7", "広島販売店", "広島県広島市"));
        shops.add(new Shop("8", "沖縄販売店", "沖縄県那覇市"));
        shops.add(new Shop("9", "京都販売店", "京都府京都市"));
        shops.add(new Shop("10", "神戸販売店", "兵庫県神戸市"));
        shops.add(new Shop("11", "横浜販売店", "神奈川県横浜市"));
        shops.add(new Shop("12", "千葉販売店", "千葉県千葉市"));
        shops.add(new Shop("13", "埼玉販売店", "埼玉県さいたま市"));
        shops.add(new Shop("14", "静岡販売店", "静岡県静岡市"));
        shops.add(new Shop("15", "岡山販売店", "岡山県岡山市"));
        shops.add(new Shop("16", "鹿児島第２販売店", "鹿児島県鹿児島市"));
        shops.add(new Shop("17", "新潟販売店", "新潟県新潟市"));
        shops.add(new Shop("18", "熊本販売店", "熊本県熊本市"));
        shops.add(new Shop("19", "長崎販売店", "長崎県長崎市"));
        shops.add(new Shop("20", "金沢販売店", "石川県金沢市"));
    }
}