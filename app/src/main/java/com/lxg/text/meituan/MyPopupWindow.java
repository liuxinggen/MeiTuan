package com.lxg.text.meituan;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：com.lxg.text.meituan
 * 时间：2017/12/28 16:10
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */

public class MyPopupWindow extends PopupWindow {

    private Activity context;
    private LayoutInflater inflater;
    private View view;
    private RecyclerView rvLeft, rvRight;
    private LinearLayout linearLayout;
    private LeftAdapter leftAdapter;

    private RightAdapter rightAdapter;
    private List<String> listLeft;
    private List<String> listRight;

    float density;
    int width;
    int height;

    public MyPopupWindow(Activity context) {
        super(context);
        this.context = context;

        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        density = dm.density;
        width = dm.widthPixels;
        height = dm.heightPixels;
        initLine();

    }

    /**
     * 左右都是recyclerview
     */
    public void initLine() {
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_popup, null);
        rvLeft = view.findViewById(R.id.rv_left);
        rvRight = view.findViewById(R.id.rv_right);
        linearLayout = view.findViewById(R.id.ll_popup);
    }

    private void initEvent() {
        leftAdapter.setOnRecyclerViewItemListenter(new OnRecyclerViewItemListenter() {
            @Override
            public void onClick(View view, int position) {
                setRightData(position);
            }
        });
    }

    /**
     * 添加右边的数据
     *
     * @param position
     */
    private void setRightData(int position) {
        String itemName = listLeft.get(position);
        int size = (int) (Math.random() * 20 + 1);
        listRight.clear();
        for (int i = 0; i < size; i++) {
            listRight.add(itemName + "--" + i);
        }
        rightAdapter.notifyDataSetChanged();
    }

    private void initData() {
        listRight = new ArrayList<>();
        leftAdapter = new LeftAdapter(context, listLeft);
        rightAdapter = new RightAdapter(context, listRight);

        rvLeft.setLayoutManager(new LinearLayoutManager(context)
//                                {
//
//            @Override
//            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//                if (getChildCount() > 0) {
//                    View firstChildView = recycler.getViewForPosition(0);
//                    measureChild(firstChildView, widthSpec, heightSpec);
//                    setMeasuredDimension(View.MeasureSpec.getSize(widthSpec),
//                            firstChildView.getMeasuredHeight() * 4);
//                } else {
//                    super.onMeasure(recycler, state, widthSpec, heightSpec);
//                }
//            }
//        }
        );
        rvRight.setLayoutManager(new LinearLayoutManager(context));
        rvLeft.setAdapter(leftAdapter);
        rvRight.setAdapter(rightAdapter);
        //默认选中第一行
        leftAdapter.setItemChecked(0, true);
    }

    public void setData(List<String> listLeft) {
        this.listLeft = listLeft;
        initData();
        initView();
        initEvent();
    }


    /**
     * 初始化PopupWindow的一些数据
     */
    private void initView() {
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(height / 2);

        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //如果窗口存在，则更新
        this.update();
        // 设置popWindow的显示和消失动画
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        //0.0-1.0
        backgroundAlpha(context, 0.5f);

        // 如果触摸位置在窗口外面则销毁
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = linearLayout.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                        backgroundAlpha(context, 1f);
                    }
                }
                return true;
            }
        });
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });


    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


}
