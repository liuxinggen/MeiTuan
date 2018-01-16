package com.lxg.text.meituan;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
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

public class LeftAdapter extends RecyclerView.Adapter<LeftViewHolder> {

    private Context context;
    private List<String> list;
    private OnRecyclerViewItemListenter onRecyclerViewItemListenter;
    /**
     * 声明默认选中的项
     */
    private int selectionItem = -1;
    /**
     * 控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
     */
    private List<Boolean> isClicks;
    /**
     * item的高度
     */
    public int itemHeight = -1;

    public LeftAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        isClicks = new ArrayList<Boolean>();
        for (int i = 0; i < list.size(); i++) {
            isClicks.add(false);
        }
    }

    @Override
    public LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_adapter_left, parent, false);
        LeftViewHolder viewHolder = new LeftViewHolder(view);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewHolder.llItemLeft
                .getLayoutParams();
        Log.i("Alex", "layoutParams.height=" + layoutParams.height
                + "  layoutParams.width=" + layoutParams.width);
        itemHeight = layoutParams.height;
        Log.i("Alex", "viewholder.height=" + itemHeight);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final LeftViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));
        //默认选中状态
        if (selectionItem > -1 & selectionItem == position) {
            getOnRecyclerViewItemListenter().onClick(holder.textView, position);
        }
        //点击事件
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemChecked(position, false);
                getOnRecyclerViewItemListenter().onClick(v, position);
                for (int i = 0; i < list.size(); i++) {
                    isClicks.set(i, false);
                }
                isClicks.set(position, true);
                notifyDataSetChanged();
            }
        });
        //将数据保存在itemView的Tag中，以便点击时进行获取
        if (isClicks.size() > 0 && isClicks.get(position)) {
            holder.textView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.textView.setBackgroundColor(Color.parseColor("#f0f0f0"));
        }

    }

    /**
     * 添加默认选中某一列
     *
     * @param selectionItem 列下标
     * @param data          true:选中；false:未选中，默认未选中
     */
    public void setItemChecked(int selectionItem, boolean data) {
        this.selectionItem = selectionItem;
        if (data) {
            isClicks.add(selectionItem, data);
        }
        notifyDataSetChanged();
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
}

class LeftViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    LinearLayout llItemLeft;

    public LeftViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_item);
        llItemLeft = itemView.findViewById(R.id.ll_item_left);
    }
}
