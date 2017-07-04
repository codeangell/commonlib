package common.lib.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NestRecyclerView extends RecyclerView {

    public NestRecyclerView(Context context) {
        this(context, null);
    }

    public NestRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setNestedScrollingEnabled(false);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
