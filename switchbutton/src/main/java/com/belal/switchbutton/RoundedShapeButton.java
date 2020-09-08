package com.belal.switchbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoundedShapeButton extends Drawable {

    private Context context;
    private Paint paint;
    private Path path;

    private int color;
    private int radius;

    public RoundedShapeButton(int color, int radius) {
        this.color = color;
        this.radius = radius;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        path = new Path();
    }
//    public RoundedShapeButton(Context context) {
//        super(context);
//        this.context = context;
//        init(null);
//    }
//
//    public RoundedShapeButton(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        init(attrs);
//    }
//
//    public RoundedShapeButton(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        init(attrs);
//    }

//    private void init(AttributeSet attrs) {
//        if(attrs == null) {
//            color = context.getResources().getColor(R.color.colorAccent);
//            radius = 25;
//        }else {
//            TypedArray a = context.getTheme().obtainStyledAttributes(
//                    attrs,
//                    R.styleable.RoundedButton,
//                    0, 0);
//            try {
//                color = a.getColor(R.styleable.RoundedButton_filled_color, context.getResources().getColor(R.color.colorAccent));
//                radius = a.getInteger(R.styleable.RoundedButton_corner, 25);
//            } finally {
//                a.recycle();
//            }
//        }
//        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(color);
//
//        path = new Path();
//    }


//    @Override
//    public void onDraw(Canvas canvas) {

//    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int height = getBounds().height();
        int width = getBounds().width();

        path.reset();
        path.moveTo(0, radius);
        path.cubicTo(0, radius, 0, 0, radius, 0);
        path.lineTo(width - radius, 0);
        path.cubicTo(width - radius, 0, width, 0, width, radius);
        path.lineTo(width, height - radius);
        path.cubicTo(width, height - radius, width, height, width - radius, height);
        path.lineTo(radius, height);
        path.cubicTo(radius, height, 0, height, 0, height - radius);
        path.close();

        canvas.drawPath(path, paint);
    }

    @Override
    protected boolean onLevelChange(int level) {
        invalidateSelf();
        return true;
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
