package com.lxg.text.meituan;

import java.io.Serializable;

/**
 * 类名：com.gstb.agriculture.entity.httpinfoentity.njzs
 * 时间：2017/12/19 11:15
 * 描述：农技知识实体类
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author Liu_xg
 */

public class NjzsEntity implements Serializable {

    private String title;
    private String type;

    public NjzsEntity(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
