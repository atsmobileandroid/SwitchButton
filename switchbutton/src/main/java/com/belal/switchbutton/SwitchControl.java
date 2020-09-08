package com.belal.switchbutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwitchControl extends LinearLayout {

    private Context context;
    private int backgroundColor;
    private int textColor;
    private int buttonColor;
    private int radius;
    private RoundedShapeButton background, buttonBackground;
    private TextView btn1, btn2;
    private String txt1, txt2;
    private int textSize;
    private OnSwitchControlChanged onSwitchControlChanged;
    private boolean secondButtonClicked;

    public SwitchControl(Context context) {
        super(context);
        this.context = context;
        init(null);
    }

    public SwitchControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public SwitchControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            backgroundColor = context.getResources().getColor(R.color.colorAccent);
            buttonColor = android.R.color.white;
            radius = 25;
        } else {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.SwitchControl,
                    0, 0);
            try {
                backgroundColor = a.getColor(R.styleable.SwitchControl_background_color, context.getResources().getColor(R.color.colorAccent));
                buttonColor = a.getColor(R.styleable.SwitchControl_button_color, context.getResources().getColor(R.color.colorAccent));
                float attrCorner = a.getDimension(R.styleable.SwitchControl_corner, 12);
                Resources r = getResources();
                radius = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        attrCorner,
                        r.getDisplayMetrics()
                );
                txt1 = a.getString(R.styleable.SwitchControl_button_1_text);
                txt2 = a.getString(R.styleable.SwitchControl_button_2_text);
                textColor = a.getColor(R.styleable.SwitchControl_button_text_color, context.getResources().getColor(R.color.colorAccent));
                textSize = (int) a.getDimension(R.styleable.SwitchControl_button_text_size, 12);
            } finally {
                a.recycle();
            }
        }

        buttonBackground = new RoundedShapeButton(buttonColor, radius);

        View rootView = inflate(context, R.layout.switch_control, this);

        background = new RoundedShapeButton(backgroundColor, radius);
        rootView.setBackground(background);

        btn1 = rootView.findViewById(R.id.btn1);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButton1Clicked();
            }
        });
        btn2 = rootView.findViewById(R.id.btn2);
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onButton2Clicked();
            }
        });

        btn1.setText(txt1);
        btn2.setText(txt2);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        btn1.setBackground(buttonBackground);

    }


    private void onButton1Clicked() {
        if(!secondButtonClicked)
            return;
        btn1.setBackground(buttonBackground);
        btn2.setBackground(null);
        if (onSwitchControlChanged != null)
            onSwitchControlChanged.onChanged(1);
        secondButtonClicked = false;
    }

    private void onButton2Clicked() {
        if(secondButtonClicked)
            return;
        btn2.setBackground(buttonBackground);
        btn1.setBackground(null);
        if (onSwitchControlChanged != null)
            onSwitchControlChanged.onChanged(2);
        secondButtonClicked = true;
    }


    public void setOnSwitchControlChanged(OnSwitchControlChanged onSwitchControlChanged) {
        this.onSwitchControlChanged = onSwitchControlChanged;
    }


}
