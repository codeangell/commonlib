package common.lib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import common.lib.DensityUtil;
import common.lib.R;
import common.lib.view.PathImageView;


/**
 * User: ShangGuanRuiPeng
 * Date: 2015/11/28
 * Time: 14:57
 * Introduction:
 */
public class ChatImageView extends PathImageView {
    private int direction;
    //圆角直径
    private final static int ROUND_DIAMETER = 30;
    //聊天箭头宽度
    private final static int WIDE = 10;

    public ChatImageView(Context context) {
        this(context, null);
    }

    public ChatImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ChatImageView);

        //获取自定义属性和默认值
        direction = mTypedArray.getInt(R.styleable.ChatImageView_direction, 0);

        mTypedArray.recycle();
        setOnTouchListener(onTouchListener);
    }

    public ChatImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }



    private View.OnTouchListener onTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    setColorFilter(null);
                    break;
                case MotionEvent.ACTION_DOWN:
                    changeLight();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setColorFilter(null);
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private void changeLight() {
        int brightness=-80;
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        setColorFilter(new ColorMatrixColorFilter(matrix));

    }


    @Override
    protected Path getPath() {
        int w = getWidth();
        int h = getHeight();

        if(direction == 1) {
            return right(w, h);
        } else if(direction == 2){
            return left(w, h);
        } else {
            return null;
        }
    }

    private Path right(int w, int h) {
        Path path = new Path();
        path.moveTo(0, 0);

        path.arcTo(new RectF(0, 0, ROUND_DIAMETER, ROUND_DIAMETER), 180, 90);

        path.lineTo(w - WIDE - ROUND_DIAMETER/2, 0);

        path.arcTo(new RectF(w - WIDE - ROUND_DIAMETER, 0, w - WIDE, ROUND_DIAMETER), -90, 90);

        path.lineTo(w - WIDE, DensityUtil.dp2px(getContext(), 10));

        path.lineTo(w, DensityUtil.dp2px(getContext(), 9));

        path.lineTo(w - WIDE, DensityUtil.dp2px(getContext(), 18));

        path.lineTo(w - WIDE, h - ROUND_DIAMETER / 2);

        path.arcTo(new RectF(w - ROUND_DIAMETER - WIDE, h - ROUND_DIAMETER, w - WIDE, h), 0, 90);

        path.lineTo(ROUND_DIAMETER/2, h);

        path.arcTo(new RectF(0, h - ROUND_DIAMETER, ROUND_DIAMETER, h), 90, 90);

        path.close();
        return path;
    }

    private Path left(int w, int h) {
        Path path = new Path();
        path.moveTo(WIDE, ROUND_DIAMETER/2);

        path.arcTo(new RectF(WIDE, 0, ROUND_DIAMETER + WIDE, ROUND_DIAMETER), 180, 90);

        path.lineTo(w - ROUND_DIAMETER/2, 0);

        path.arcTo(new RectF(w - ROUND_DIAMETER, 0, w, ROUND_DIAMETER), -90, 90);

//        path.lineTo(w - WIDE, DensityUtil.dip2px(getContext(), 10));
//
//        path.lineTo(w, DensityUtil.dip2px(getContext(), 9));
//
//        path.lineTo(w - WIDE, DensityUtil.dip2px(getContext(), 18));

        path.lineTo(w, h - ROUND_DIAMETER / 2);

        path.arcTo(new RectF(w - ROUND_DIAMETER, h - ROUND_DIAMETER, w, h), 0, 90);

        path.lineTo(ROUND_DIAMETER / 2, h);

        path.arcTo(new RectF(WIDE, h - ROUND_DIAMETER, ROUND_DIAMETER, h), 90, 90);

        path.lineTo(WIDE, DensityUtil.dp2px(getContext(), 18));

        path.lineTo(0, DensityUtil.dp2px(getContext(), 9));

        path.lineTo(WIDE, DensityUtil.dp2px(getContext(), 10));

        path.close();
        return path;
    }


}
