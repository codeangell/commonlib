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
public class CircleImageView extends PathImageView {


    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    @Override
    protected Path getPath() {
        int w = getWidth();
        int h = getHeight();
        Path path = new Path();
        path.moveTo(0, 0);
        path.arcTo(new RectF(0, 0, w, h), 0, 360);
        return path;
    }
}
