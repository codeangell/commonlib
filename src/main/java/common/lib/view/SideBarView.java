package common.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import common.lib.DensityUtil;
import common.lib.R;
import common.lib.ScreenUtils;

public class SideBarView extends View {
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    public String[] b = {"↑", "#"};
//    public String[] b = {"↑", "A", "B", "C", "D", "E", "F", "G", "H", "I",
//            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//            "W", "X", "Y", "Z", "#"};
    private int choose = -1;
    private Paint paint = new Paint();

    private TextView mTextDialog;
    private Context context;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }


    public SideBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public SideBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SideBarView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if(heightModel == MeasureSpec.AT_MOST) {
            heightSize = b.length * DensityUtil.sp2px(context, 18);
            if(heightSize > ScreenUtils.getScreenHeight(context) - DensityUtil.dp2px(context, 48) -ScreenUtils.getStatusHeight(context) - DensityUtil.dp2px(context, 52)) {
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
            }
        }
        setMeasuredDimension(widthSize, heightSize);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.length;
//        if(singleHeight > DensityUtil.sp2px(context, 18)) {
//            singleHeight = DensityUtil.sp2px(context, 18);
//        }
        int size = b.length;
        for (int i = 0; i < size; i++) {
            paint.setColor(context.getResources().getColor(R.color.color_979797));
            paint.setAntiAlias(true);
            if(i != 0){
                paint.setTextSize(DensityUtil.sp2px(context, 12));
            } else {
                paint.setTextSize(DensityUtil.sp2px(context, 16));
            }
            if (i == choose) {
                paint.setColor(context.getResources().getColor(R.color.white));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    public void setB(String[] b) {
        this.b = b;
        post(new Runnable() {
            @Override
            public void run() {
                requestLayout();
                invalidate();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                setBackgroundResource(R.color.color_30000000);
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }


    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }


    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}