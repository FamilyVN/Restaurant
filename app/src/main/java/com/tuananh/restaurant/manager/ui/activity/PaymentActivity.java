package com.tuananh.restaurant.manager.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tuananh.restaurant.manager.R;
import com.tuananh.restaurant.manager.data.Constants;
import com.tuananh.restaurant.manager.model.Constant;

public class PaymentActivity extends BaseActivity implements View.OnClickListener {
    private String mNameBoard;
    private int mIdBoard, mTotalMoney, mCustomersPay, mRefundsToCustomers;
    private String mStringCustomersPay = "";
    private TextView mTextViewNameBoard, mTextViewTotalMoney, mTextViewCustomersPay,
        mTextViewRefundsToCustomers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        loadData();
        initViews();
        onListener();
    }

    private void loadData() {
        Intent intent = getIntent();
        if (intent == null) return;
        mTotalMoney = intent.getIntExtra(Constants.TOTAL_MONEY, Constants.TOTAL_MONEY_DEFAULT);
        mNameBoard = intent.getStringExtra(Constants.NAME_BOARD_SELECTED);
        mIdBoard = intent.getIntExtra(Constants.ID_BOARD_SELECTED, Constant.ID_BOARD_DEFAULT);
        mTotalMoney = intent.getIntExtra(Constants.TOTAL_MONEY, Constants.TOTAL_MONEY_DEFAULT);
    }

    private void initViews() {
        mTextViewTotalMoney = (TextView) findViewById(R.id.text_total_money_payment_activity);
        mTextViewCustomersPay = (TextView) findViewById(R.id.text_customers_pay_payment_activity);
        mTextViewRefundsToCustomers =
            (TextView) findViewById(R.id.text_refunds_to_customers_payment_activity);
        if (TextUtils.isEmpty(mNameBoard)) {
            findViewById(R.id.layout_name_board_payment_activity).setVisibility(View.GONE);
        } else {
            mTextViewNameBoard = (TextView) findViewById(R.id.text_name_board_payment_activity);
            mTextViewNameBoard.setText(mNameBoard);
        }
        mTextViewTotalMoney.setText(String.valueOf(mTotalMoney));
    }

    private void onListener() {
        findViewById(R.id.button_pay_number_1).setOnClickListener(this);
        findViewById(R.id.button_pay_number_2).setOnClickListener(this);
        findViewById(R.id.button_pay_number_3).setOnClickListener(this);
        findViewById(R.id.button_pay_number_4).setOnClickListener(this);
        findViewById(R.id.button_pay_number_5).setOnClickListener(this);
        findViewById(R.id.button_pay_number_6).setOnClickListener(this);
        findViewById(R.id.button_pay_number_7).setOnClickListener(this);
        findViewById(R.id.button_pay_number_8).setOnClickListener(this);
        findViewById(R.id.button_pay_number_9).setOnClickListener(this);
        findViewById(R.id.button_pay_number_delete).setOnClickListener(this);
        findViewById(R.id.button_pay_number_delete_all).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_pay_number_1:
                totalMoneyCustomersPay(getString(R.string.text_button_pay_number_1));
                break;
            case R.id.button_pay_number_2:
                mStringCustomersPay += getString(R.string.text_button_pay_number_2);
                break;
            case R.id.button_pay_number_3:
                mStringCustomersPay += getString(R.string.text_button_pay_number_3);
                break;
            case R.id.button_pay_number_4:
                mStringCustomersPay += getString(R.string.text_button_pay_number_4);
                break;
            case R.id.button_pay_number_5:
                mStringCustomersPay += getString(R.string.text_button_pay_number_5);
                break;
            case R.id.button_pay_number_6:
                mStringCustomersPay += getString(R.string.text_button_pay_number_6);
                break;
            case R.id.button_pay_number_7:
                mStringCustomersPay += getString(R.string.text_button_pay_number_7);
                break;
            case R.id.button_pay_number_8:
                mStringCustomersPay += getString(R.string.text_button_pay_number_8);
                break;
            case R.id.button_pay_number_9:
                mStringCustomersPay += getString(R.string.text_button_pay_number_9);
                break;
            case R.id.button_pay_number_delete:
                StringBuilder stringBuilder = new StringBuilder(mStringCustomersPay);
                int length = stringBuilder.length() - 1;
                if (length >= 0) {
                    mStringCustomersPay =
                        stringBuilder.deleteCharAt(length).toString();
                }
                break;
            case R.id.button_pay_number_delete_all:
                mStringCustomersPay = "";
                break;
            default:
                mStringCustomersPay = "";
                break;
        }
        mTextViewCustomersPay.setText(mStringCustomersPay);
    }

    private void totalMoneyCustomersPay(String number) {
        mStringCustomersPay += number;
    }
}
