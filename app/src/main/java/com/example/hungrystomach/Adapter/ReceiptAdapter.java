package com.example.hungrystomach.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrystomach.Model.Receipt;
import com.example.hungrystomach.R;
import com.example.hungrystomach.Track_Order_Activity;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.GridViewHolder > {

    private Context mContext;
    private List<Receipt> receiptList;


    public static final String EXTRA_COOKER_UID = "NoCookerUID";
    public static final String EXTRA_STATUS = "NoStatus";
    public static final String EXTRA_BILLDATE = "NoBillDate";
    public static final String EXTRA_RECEIPT_KEY = "NoKey";
    public ReceiptAdapter(Context mContext, List<Receipt> receiptList) {
        this.mContext = mContext;
        this.receiptList = receiptList;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_item_receipt, parent, false);
        return new GridViewHolder(v);
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        private TextView ReceiptNum;
        private TextView ReceiptDate;
        private TextView ReceiptTotal;
        private TextView ReceiptUploader;
        private TextView ReceiptStatus;
        public GridViewHolder (View itemView){
            super(itemView);
            ReceiptNum = itemView.findViewById(R.id.display_receipt);
            ReceiptDate = itemView.findViewById(R.id.receipt_date);
            ReceiptTotal = itemView.findViewById(R.id.receipt_price);
            ReceiptUploader = itemView.findViewById(R.id.receipt_name);
            ReceiptStatus = itemView.findViewById(R.id.receipt_status);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.GridViewHolder holder, int position) {
        Receipt receipt = receiptList.get(position);
        holder.ReceiptNum.setText("Receipt#" + String.valueOf(receipt.getReceipt_number()));
        holder.ReceiptDate.setText(receipt.getBillDate());
        holder.ReceiptTotal.setText("$" +String.valueOf(receipt.getGrand_total()));
        holder.ReceiptUploader.setText(receipt.getUuid());
        holder.ReceiptStatus.setText(receipt.getHis_status());
        final String cooker_uid = String.valueOf(receipt.getUuid());
        final String cooker_status = String.valueOf(receipt.getHis_status());
        final String billdate = String.valueOf(receipt.getBillDate());
        final String random_key = String.valueOf(receipt.getRandom_key());

        //click track_order.xml
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent track_order = new Intent(mContext, Track_Order_Activity.class);
                track_order.putExtra(EXTRA_COOKER_UID, cooker_uid);
                track_order.putExtra(EXTRA_STATUS, cooker_status);
                track_order.putExtra(EXTRA_BILLDATE, billdate);
                track_order.putExtra(EXTRA_RECEIPT_KEY, random_key);
                mContext.startActivity(track_order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receiptList.size();
    }
}