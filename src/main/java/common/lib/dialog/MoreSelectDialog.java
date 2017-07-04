package common.lib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import common.lib.R;
import common.lib.ScreenUtils;

/**
 * User: ShangGuanRuiPeng
 * Date: 2016/12/6
 * Time: 9:20
 * Introduction:
 */
public class MoreSelectDialog extends Dialog {

    private View rootView;
    private TextView tv_title;
    private ListView lv_more_select;

    private MoreSelectAdapter moreSelectAdapter;
    private List<String> items;

    public MoreSelectDialog(Activity context) {
        super(context, R.style.style_select);
        setOwnerActivity(context);
        items = new ArrayList<>();
    }

    public static MoreSelectDialog getInstance(Activity context) {
        return new MoreSelectDialog(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diglog_more_select);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); //可设置dialog的位置
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(getContext()) * 3 / 4;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        lv_more_select = (ListView) findViewById(R.id.lv_more_select);
        tv_title = (TextView) findViewById(R.id.tv_title);

        moreSelectAdapter = new MoreSelectAdapter(getContext(), R.layout.item_more_select, R.id.tv_select, new ArrayList<String>());
        lv_more_select.setAdapter(moreSelectAdapter);
    }

    public MoreSelectDialog show(MoreSelectItemClickListener listener, MoreSelectEnum... enums) {
        if (isShowing() || getOwnerActivity() == null || getOwnerActivity().isFinishing()) {
            return this;
        }
        show();
        this.listener = listener;
        replaceAll(enums);
        return this;

    }

    public void showByArr(MoreSelectItemClickListener listener, MoreSelectEnum[] enums) {
        if (isShowing() || getOwnerActivity() == null || getOwnerActivity().isFinishing()) {
            return;
        }
        show();
        this.listener = listener;
        replaceAll(enums);
    }

    public MoreSelectDialog setTitleText(CharSequence charSequence) {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(charSequence);
        return this;
    }

    private void setOnItemClick(AdapterView.OnItemClickListener listener) {
        if (lv_more_select != null) lv_more_select.setOnItemClickListener(listener);
    }

    private void replaceAll(MoreSelectEnum[] enums) {
        moreSelectAdapter.clear();
        items.clear();
        for (int i = 0; i < enums.length; i++) {
            if (enums[i] == null || enums[i] == MoreSelectEnum.NONE) {
                continue;
            }
            items.add(enums[i].getDesc());
        }
        moreSelectAdapter.addAll(items);
        setOnItemClick(new MoreSelectItemClick(items));
    }

    private void replaceAll(List<String> stringList) {
        moreSelectAdapter.clear();
        moreSelectAdapter.addAll(stringList);
    }


    public class MoreSelectItemClick implements AdapterView.OnItemClickListener {
        private List<String> items;

        public MoreSelectItemClick(List<String> items) {
            this.items = items;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            listener.selectItem(MoreSelectEnum.valueString(items.get(position)));
            if (isShowing()) dismiss();
        }
    }

    MoreSelectItemClickListener listener;

    class MoreSelectAdapter extends ArrayAdapter<String> {

        public MoreSelectAdapter(Context context, int resource) {
            super(context, resource);
        }

        public MoreSelectAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }
    }

}
