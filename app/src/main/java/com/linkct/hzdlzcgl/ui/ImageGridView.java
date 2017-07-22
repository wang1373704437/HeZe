package com.linkct.hzdlzcgl.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ImageGridView extends GridView{

	public ImageGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ImageGridView(Context context) {
		super(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST); 
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
