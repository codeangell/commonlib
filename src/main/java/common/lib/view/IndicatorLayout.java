package common.lib.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import common.lib.DensityUtil;
import common.lib.R;


/**
 * User: ShangGuanRuiPeng
 * Date: 2017/4/20
 * Time: 9:26
 * Introduction:
 */
public class IndicatorLayout extends LinearLayout {

    private int count; //种数量

    ViewPager viewPager;

    public IndicatorLayout(Context context) {
        this(context, null);
    }

    public IndicatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    LayoutParams params;
    TextView head;
    TextView tail;

    public IndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.rightMargin = DensityUtil.dp2px(getContext(), 0);
        head = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.layout_indicator, null);
        head.setLayoutParams(params);
        head.setText(". . .");
        tail = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.layout_indicator, null);
        tail.setLayoutParams(params);
        tail.setText(". . .");
        head.setVisibility(GONE);
        tail.setVisibility(GONE);
        initView();
    }

    TextView[] textViews;

    private void initView() {
        removeAllViews();
        if (count > 1) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
        textViews = new TextView[count];
        for (int i = 0; i < count; i++) {
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.layout_indicator, null);
            textView.setLayoutParams(params);
            textView.setText((i + 1) + "");
            textViews[i] = textView;
            textView.setVisibility(i < 8 || i == count - 1 ? VISIBLE : GONE);
            textView.setOnTouchListener(new ItemTouchListener(i));
            addView(textView, i);
        }
        if (count > 3) {
            addView(head, 1);
            addView(tail, getChildCount() - 1);
        }
    }


    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        setSelectedItem(viewPager.getCurrentItem());
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        initView();
    }


    public void setSelectedItem(int selectedItem) {
        for (int i = 0; i < count; i++) {
//            textViews[i].setBackgroundResource(i == selectedItem ? R.drawable.shape_indicator_textview_press : R.drawable.shape_indicator_textview_normal);
            textViews[i].setTextColor(i == selectedItem ? getResources().getColor(R.color.colorPrimary) : getResources().getColor(R.color.white));
            // || selectedItem + i < 10 || (selectedItem > count - 6 && count - i < 10)
            if (count < 10 || Math.abs(selectedItem - i) < 5 || i == 0 || i == count - 1) {
                textViews[i].setVisibility(VISIBLE);
            } else {
                textViews[i].setVisibility(GONE);
            }
        }
        if (count > 3) {
            if (textViews[1].getVisibility() == GONE) {
                textViews[2].setVisibility(GONE);
            }
            if (textViews[count - 2].getVisibility() == GONE) {
                textViews[count - 3].setVisibility(GONE);
            }
            head.setVisibility(textViews[1].getVisibility() == GONE ? VISIBLE : GONE);
            tail.setVisibility(textViews[count - 2].getVisibility() == GONE ? VISIBLE : GONE);
        }

    }

    class ItemTouchListener implements OnTouchListener {
        private int positiotn;

        public ItemTouchListener(int positiotn) {
            this.positiotn = positiotn;
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (viewPager != null) {
                viewPager.setCurrentItem(positiotn);
                setSelectedItem(positiotn);
            }
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final float y = event.getY();
        final float x = event.getX();

        Rect frame = new Rect();
        for (int i = count - 1; i >= 0; i--) {
            final View child = textViews[i];

            child.getHitRect(frame);

            //如Touch到屏幕上的点在该子View上面
            if (frame.contains((int) x, (int) y)) {
                viewPager.setCurrentItem(i);
                setSelectedItem(i);
            }
        }

        return true;
    }

}