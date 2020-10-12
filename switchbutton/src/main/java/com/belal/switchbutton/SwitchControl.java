package com.belal.switchbutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SwitchControl extends LinearLayout {

    private Context context;
    private int backgroundColor;
    private int textColor;
    private int buttonColor;
    private int radius;
    private RoundedShapeButton background, selectedBackground;
    private String[] buttonsNames;
    private int textSize;
    private OnSwitchControlChanged onSwitchControlChanged;
    private List<TextView> buttons;
    private int index;

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
                buttonsNames = getResources().getStringArray(a.getResourceId(R.styleable.SwitchControl_buttons_names, 0));
                textColor = a.getColor(R.styleable.SwitchControl_button_text_color, context.getResources().getColor(R.color.colorAccent));
                textSize = (int) a.getDimension(R.styleable.SwitchControl_button_text_size, 12);
            } finally {
                a.recycle();
            }
        }

        selectedBackground = new RoundedShapeButton(buttonColor, radius);

        View rootView = inflate(context, R.layout.switch_control, this);
        LinearLayout container = rootView.findViewById(R.id.container);


        int padding5dp = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5,
                getResources().getDisplayMetrics()
        );
        int padding1dp = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                1.5f,
                getResources().getDisplayMetrics()
        );

        background = new RoundedShapeButton(backgroundColor, radius);
        container.setBackground(background);
        container.setPadding(padding1dp,padding1dp,padding1dp,padding1dp);

        buttons = new ArrayList<>();

        for (int i = 0; i < buttonsNames.length; i++) {
            index = i;
            final TextView tv = new TextView(context);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
            tv.setTag(index);
            tv.setText(buttonsNames[index]);
            tv.setTextColor(textColor);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tv.setBackground(background);
            tv.setGravity(Gravity.CENTER);
            tv.setMaxLines(1);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setPadding(padding5dp, padding5dp, padding5dp, padding5dp);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClicked((int)tv.getTag());
                }
            });

            buttons.add(tv);
            container.addView(tv);


        }
        if(buttons.size() > 0)
            buttons.get(0).setBackground(selectedBackground);
    }


    private void onButtonClicked(int index) {
        for(TextView btn : buttons) {
            if((int)btn.getTag() == index)
                btn.setBackground(selectedBackground);
            else
                btn.setBackground(background);
        }
        if (onSwitchControlChanged != null)
            onSwitchControlChanged.onChanged(index);
    }


    public void setOnSwitchControlChanged(OnSwitchControlChanged onSwitchControlChanged) {
        this.onSwitchControlChanged = onSwitchControlChanged;
    }


}
