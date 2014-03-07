package com.example.paint2;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
Context context;
private int[] GalImages = new int[] {
R.drawable.apple_3,
R.drawable.banana_3,
R.drawable.grapes_3,
R.drawable.pomegranate_3,
R.drawable.lychee_3,
R.drawable.muskmelon_3,
R.drawable.orange_3,
R.drawable.papaya_3,
R.drawable.pineapple_3,
R.drawable.sapodilla_3,
R.drawable.strawberry_3,
R.drawable.custardapple_3,
R.drawable.watermelon_3,
};
ImageAdapter(Context context,int lang){
	this.context=context;
	if(lang==0)
	{
		GalImages[0]=R.drawable.apple_4;
		GalImages[1]=R.drawable.banana_4;
		GalImages[2]=R.drawable.grapes_4;
		GalImages[3]=R.drawable.pomegranate_4;
		GalImages[4]=R.drawable.lychee_4;
		GalImages[5]=R.drawable.muskmelon_4;
		GalImages[6]=R.drawable.orange_4;
		GalImages[7]=R.drawable.papaya_4;
		GalImages[8]=R.drawable.pineapple_4;
		GalImages[9]=R.drawable.sapodilla_4;
		GalImages[10]=R.drawable.strawberry_4;
		GalImages[11]=R.drawable.custardapple_4;
		GalImages[12]=R.drawable.watermelon_4;
	}
}
@Override
public int getCount() {
return GalImages.length;
}
 
@Override
public boolean isViewFromObject(View view, Object object) {
return view == ((ImageView) object);
}
 
@Override
public Object instantiateItem(ViewGroup container, int position) {
ImageView imageView = new ImageView(context);
int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
padding = 0;
imageView.setPadding(padding, padding, padding, padding);
imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
imageView.setImageResource(GalImages[position]);
((ViewPager) container).addView(imageView, 0);
return imageView;
}
 
@Override
public void destroyItem(ViewGroup container, int position, Object object) {
((ViewPager) container).removeView((ImageView) object);
}
}