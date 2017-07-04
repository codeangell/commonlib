package common.lib.view;

import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * User: ShangGuanRuiPeng
 * Date: 2016/6/1
 * Time: 15:35
 * Introduction:
 */
public class RoundRectImageView extends PathImageView {

    private int direction;
    //圆角直径
    private final static int ROUND_DIAMETER = 30;

    public RoundRectImageView(Context context) {
        this(context, null);
    }

    public RoundRectImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RoundRectImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    @Override
    protected Path getPath() {
        int w = getWidth();
        int h = getHeight();
        Path path = new Path();
        path.moveTo(0, 0);

        path.arcTo(new RectF(0, 0, ROUND_DIAMETER, ROUND_DIAMETER), 180, 90);

        path.lineTo(w - ROUND_DIAMETER/2, 0);

        path.arcTo(new RectF(w - ROUND_DIAMETER, 0, w, ROUND_DIAMETER), -90, 90);

        path.lineTo(w, h - ROUND_DIAMETER/2);

        path.arcTo(new RectF(w - ROUND_DIAMETER, h - ROUND_DIAMETER, w, h), 0, 90);

        path.lineTo(ROUND_DIAMETER/2, h);

        path.arcTo(new RectF(0, h - ROUND_DIAMETER, ROUND_DIAMETER, h), 90, 90);

        path.close();
        return path;
    }
}
