package jp.developer.bbee.myapplication;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.developer.bbee.myapplication.sales.SalesShopDialog;
import jp.developer.bbee.myapplication.sales.shop.Shop;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Shop> _shop = new MutableLiveData<>();
    public LiveData<Shop> shop = _shop;

    public void setShop(Shop shop) {
        this._shop.setValue(shop);
    }
    public String getShopId() {
        return _shop.getValue().getId();
    }
    public String getShopName() {
        return _shop.getValue().getName();
    }
    public String getShopAddress() {
        return _shop.getValue().getAddress();
    }


    private SalesShopDialog salesShopDialog;
    public void setSalesShopDialog(SalesShopDialog salesShopDialog) {
        this.salesShopDialog = salesShopDialog;
    }
    public SalesShopDialog getSalesShopDialog() {
        return salesShopDialog;
    }
    public void showSalesShopDialog(FragmentManager fragmentManager) {
        salesShopDialog.show(fragmentManager, null);
    }
    public void dismissedSalesShopDialog() {
        salesShopDialog = null;
    }
}
