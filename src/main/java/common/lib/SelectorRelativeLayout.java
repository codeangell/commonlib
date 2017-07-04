package common.lib;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * User: ShangGuanRuiPeng
 * Date: 2017/6/8
 * Time: 14:38
 * Introduction:自带点击变换颜色
 */
public class SelectorRelativeLayout extends RelativeLayout {

    public SelectorRelativeLayout(Context context) {
        this(context, null);
    }

    public SelectorRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(onTouchListener);
    }



    private OnTouchListener onTouchListener=new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    getBackground().setColorFilter(null);
                    break;
                case MotionEvent.ACTION_DOWN:
                    changeLight();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    getBackground().setColorFilter(null);
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private void changeLight() {
        int brightness=-30;
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        getBackground().setColorFilter(new ColorMatrixColorFilter(matrix));
    }
}
