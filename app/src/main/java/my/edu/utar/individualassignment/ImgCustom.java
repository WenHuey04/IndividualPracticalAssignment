package my.edu.utar.individualassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ImgCustom extends ImageView {
    public ImgCustom(Context context) {
        super(context);
    }

    public ImgCustom(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImgCustom(Context context, @Nullable AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int a = getMeasuredWidth();
        setMeasuredDimension(a,a);
    }
}
