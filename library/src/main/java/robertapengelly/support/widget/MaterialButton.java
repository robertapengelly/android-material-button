package robertapengelly.support.widget;

import  android.content.Context;
import  android.content.res.ColorStateList;
import  android.content.res.TypedArray;
import  android.graphics.Color;
import  android.graphics.drawable.Drawable;
import  android.os.Build;
import  android.util.AttributeSet;
import  android.view.MotionEvent;
import  android.view.accessibility.AccessibilityEvent;
import  android.view.accessibility.AccessibilityNodeInfo;

import  robertapengelly.support.graphics.drawable.GradientDrawable;
import  robertapengelly.support.graphics.drawable.LollipopDrawable;
import  robertapengelly.support.graphics.drawable.RippleDrawable;
import  robertapengelly.support.materialbutton.R;
import  robertapengelly.support.view.DrawableHotspotTouch;

public class MaterialButton extends MaterialTextView {

    private DrawableHotspotTouch mDrawableHotspotTouch;
    
    public MaterialButton(Context context) {
        this(context, null);
    }
    
    public MaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }
    
    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialButton, defStyleAttr, 0);
        
        int count = a.getIndexCount();
        
        for (int i = 0; i < count; ++i) {
        
            int attr = a.getIndex(i);
            
            if ((attr == R.styleable.MaterialButton_android_background) || (attr == R.styleable.MaterialButton_background)) {
            
                int resid = a.getResourceId(attr, 0);
                
                if (resid == R.drawable.btn_default_material) {
                
                    int color = getColorFromAttrRes(context, R.attr.colorControlHighlight);
                    
                    if (color == 0)
                        if (Build.VERSION.SDK_INT >= 21)
                            color = getColorFromAttrRes(context, android.R.attr.colorControlHighlight);
                    
                    if (color == 0) {
                    
                        final float[] hsv = new float[3];
                        final int themeColorBackground = getColorFromAttrRes(context, android.R.attr.windowBackground);
                        
                        Color.colorToHSV(themeColorBackground, hsv);
                        
                        if (hsv[2] > 0.5f) {
                        
                            if (Build.VERSION.SDK_INT >= 23)
                                color = getResources().getColor(R.color.ripple_material_light, context.getTheme());
                            else
                                //noinspection deprecation
                                color = getResources().getColor(R.color.ripple_material_light);
                        
                        } else {
                        
                            if (Build.VERSION.SDK_INT >= 23)
                                color = getResources().getColor(R.color.ripple_material_dark, context.getTheme());
                            else
                                //noinspection deprecation
                                color = getResources().getColor(R.color.ripple_material_dark);
                        
                        }
                    
                    }
                    
                    RippleDrawable ripple = (RippleDrawable) getBackground();
                    ripple.setColor(ColorStateList.valueOf(color));
                    
                    color = getColorFromAttrRes(context, R.attr.colorButtonNormal);
                    
                    if (color == 0)
                        if (Build.VERSION.SDK_INT >= 21)
                            color = getColorFromAttrRes(context, android.R.attr.colorButtonNormal);
                    
                    if (color == 0) {
                    
                        final float[] hsv = new float[3];
                        final int themeColorBackground = getColorFromAttrRes(context, android.R.attr.windowBackground);
                        
                        Color.colorToHSV(themeColorBackground, hsv);
                        
                        if (hsv[2] > 0.5f) {
                        
                            if (Build.VERSION.SDK_INT >= 23)
                                color = getResources().getColor(R.color.btn_default_material_light, context.getTheme());
                            else
                                //noinspection deprecation
                                color = getResources().getColor(R.color.btn_default_material_light);
                        
                        } else {
                        
                            if (Build.VERSION.SDK_INT >= 23)
                                color = getResources().getColor(R.color.btn_default_material_dark, context.getTheme());
                            else
                                //noinspection deprecation
                                color = getResources().getColor(R.color.btn_default_material_dark);
                        
                        }
                    
                    }
                    
                    GradientDrawable shape = (GradientDrawable) ripple.findDrawableByLayerId(R.id.btn_ripple_shape);
                    shape.setColor(ColorStateList.valueOf(color));
                
                }
            
            }
        
        }
        
        a.recycle();
        
        if (getBackground() instanceof LollipopDrawable)
            mDrawableHotspotTouch = new DrawableHotspotTouch((LollipopDrawable) getBackground());
    
    }
    
    private static int getColorFromAttrRes(Context context, int attr) {
    
        TypedArray a = context.obtainStyledAttributes(new int[] { attr });
        
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    
    }
    
    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        
        event.setClassName(MaterialButton.class.getName());
    
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        
        if (Build.VERSION.SDK_INT < 14)
            return;
        
        info.setClassName(MaterialButton.class.getName());
    
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return (((mDrawableHotspotTouch != null) && mDrawableHotspotTouch.onTouch(this, event)) || super.onTouchEvent(event));
    }
    
    @Override
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        
        if (getBackground() instanceof LollipopDrawable)
            mDrawableHotspotTouch = new DrawableHotspotTouch((LollipopDrawable) getBackground());
        else
            mDrawableHotspotTouch = null;
    
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        
        if (getBackground() instanceof LollipopDrawable)
            mDrawableHotspotTouch = new DrawableHotspotTouch((LollipopDrawable) getBackground());
        else
            mDrawableHotspotTouch = null;
    
    }
    
    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
        
        if (getBackground() instanceof LollipopDrawable)
            mDrawableHotspotTouch = new DrawableHotspotTouch((LollipopDrawable) getBackground());
        else
            mDrawableHotspotTouch = null;
    
    }

}