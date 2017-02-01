package robertapengelly.support.widget;

import  android.content.Context;
import  android.os.Build;
import  android.util.AttributeSet;
import  android.view.accessibility.AccessibilityEvent;
import  android.view.accessibility.AccessibilityNodeInfo;

public class MaterialButton extends MaterialTextView {

     public MaterialButton(Context context) {
        this(context, null);
    }
    
    public MaterialButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }
    
    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

}