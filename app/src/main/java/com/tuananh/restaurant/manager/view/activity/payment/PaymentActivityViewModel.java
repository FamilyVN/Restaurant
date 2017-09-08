package com.tuananh.restaurant.manager.view.activity.payment;

import android.text.TextUtils;

import com.restaurant.tuananh.mvvm.BaseViewModel;
import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.database.DatabaseManager;
import com.tuananh.restaurant.manager.databinding.ActivityPaymentBinding;
import com.tuananh.restaurant.manager.model.Constant;
import com.tuananh.restaurant.manager.model.board.Board;
import com.tuananh.restaurant.manager.utils.CommonUtils;
import com.tuananh.restaurant.manager.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuanAnh on 5/11/2017.
 */
public class PaymentActivityViewModel
    extends BaseViewModel<ActivityPaymentBinding, PaymentActivity> {
    private static final int MAX_LENGTH = 10;
    private String mNameBoard;
    private String mTotalCustomerPay;
    private String mCustomersPay;
    private String mRefundsToCustomers;
    private int mIdBoard;
    private List<String> mCustomersPayList = new ArrayList<>();

    @Override
    public void onAttached(boolean isFirst) {
        super.onAttached(isFirst);
        if (isFirst) {
            updateCost(false);
        }
    }

    public String getNameBoard() {
        return mNameBoard;
    }

    public void setNameBoard(String nameBoard) {
        mNameBoard = nameBoard;
        notifyChange();
    }

    public int getIdBoard() {
        return mIdBoard;
    }

    public void setIdBoard(int idBoard) {
        mIdBoard = idBoard;
        notifyChange();
    }

    public String getTotalCustomerPay() {
        return mTotalCustomerPay;
    }

    public void setTotalCustomerPay(String totalCustomerPay) {
        mTotalCustomerPay = totalCustomerPay;
        notifyChange();
    }

    public String getCustomersPay() {
        return mCustomersPay;
    }

    public void setCustomersPay(String customersPay) {
        mCustomersPay = customersPay;
        notifyChange();
    }

    public String getRefundsToCustomers() {
        return mRefundsToCustomers;
    }

    public void setRefundsToCustomers(String refundsToCustomers) {
        mRefundsToCustomers = refundsToCustomers;
        notifyChange();
    }

    public void updatePay(String values, int type) {
        switch (type) {
            case Constant.TYPE_ADD:
                int length = mCustomersPayList.size();
                if (length < MAX_LENGTH &&
                    CommonUtils.convertListToString(mCustomersPayList).length() < MAX_LENGTH) {
                    mCustomersPayList.add(values);
                }
                updateCost(false);
                break;
            case Constant.TYPE_REMOVE:
                int size = mCustomersPayList.size();
                if (size >= 1) {
                    mCustomersPayList.remove(size - 1);
                }
                updateCost(false);
                break;
            case Constant.TYPE_CLEAR_ALL:
                mCustomersPayList.clear();
                updateCost(false);
                break;
            case Constant.TYPE_PAY_EXACTLY:
                mCustomersPayList.clear();
                updateCost(true);
                break;
            case Constant.TYPE_PAY:
                onPay();
                break;
        }
    }

    private void onPay() {
        if (TextUtils.equals(getBinding().layoutCustomersPay.textContent.getText(), "0 đ")) {
            ToastUtils.showMessages(getContext(), R.string.msg_user_not_edit_money);
            return;
        }
        if (mIdBoard != Board.ID_BOARD_DEFAULT) {
            DatabaseManager.getInstance(getContext()).deleteBoardCommodity(mIdBoard);
        }
        getView().openActivity();
    }

    public void updateCost(boolean isPayExactly) {
        String customersPay = CommonUtils.convertListToString(mCustomersPayList);
        getView().updateTextCustomersPay(
            CommonUtils.isEmptyList(mCustomersPayList) ? "0" : customersPay, isPayExactly);
        getView().updateTextRefundsToCustomer(customersPay, isPayExactly);
    }
}
