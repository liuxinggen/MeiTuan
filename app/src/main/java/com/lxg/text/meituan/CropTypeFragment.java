package com.lxg.text.meituan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.io.Serializable;
import java.util.List;


/**
 * 类名： CropTypeFragment
 * 时间：2017/12/19 11:22
 * 描述：农作物大全
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */
public class CropTypeFragment extends Fragment {


    public static CropTypeFragment newInstance(List<NjzsEntity> njzsEntities, int positon) {
        CropTypeFragment cropTypeFragment = new CropTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("njzsEntities", (Serializable) njzsEntities);
        bundle.putInt("positon", positon);
        cropTypeFragment.setArguments(bundle);
        return cropTypeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        return view;
    }

}
