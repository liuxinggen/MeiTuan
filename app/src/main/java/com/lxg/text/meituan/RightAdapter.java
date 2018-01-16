package com.lxg.text.meituan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.List;

/**
 * 类名：com.lxg.text.meituan
 * 时间：2017/12/28 16:25
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.RightViewHolder> {

    private Context context;
    private List<String> list;
    private OnRecyclerViewItemListenter onRecyclerViewItemListenter;

    public RightAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RightViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_adapter_right, parent, false));
    }

    @Override
    public void onBindViewHolder(RightViewHolder holder, final int position) {

        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getOnRecyclerViewItemListenter().onClick(v, position);
            }
        });
        hasItemHeight(holder.textView);

    }

    public int itemHeight;

    /**
     * 获取item的高度信息
     */
    private void hasItemHeight(final View view) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                itemHeight = view.getMeasuredHeight();
                return true;
            }
        });
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public OnRecyclerViewItemListenter getOnRecyclerViewItemListenter() {
        return onRecyclerViewItemListenter;
    }

    public void setOnRecyclerViewItemListenter(OnRecyclerViewItemListenter onRecyclerViewItemListenter) {
        this.onRecyclerViewItemListenter = onRecyclerViewItemListenter;
    }

    class RightViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public RightViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_item);
        }
    }
}

