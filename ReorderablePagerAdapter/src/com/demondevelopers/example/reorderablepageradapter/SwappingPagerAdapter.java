package com.demondevelopers.example.rerderablepageradapter;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewGroup;


public abstract class SwappingPagerAdapter extends StableFragmentStatePagerAdapter
{
	private static final String TAG = SwappingPagerAdapter.class.getSimpleName();
	
	private int[] mPosMapping;
	private int mCurrentPos = 0;
	
	
	public SwappingPagerAdapter(FragmentManager fm)
	{
		super(fm);
		int count = getCount();
		mPosMapping = new int[count];
		for(int i = 0; i < count; i++){
			mPosMapping[i] = i;
		}
	}
	
	protected int getItemMapping(int position)
	{
		int found = -1;
		for(int i = 0; i < mPosMapping.length; i++){
			if(mPosMapping[i] == position){
				found = i;
				break;
			}
		}
		return found;
	}
	
	public void swap()
	{
		int swapWith;
		if(mCurrentPos + 1 < getCount() - 1){
			swapWith = mCurrentPos + 1;
		}else{
			swapWith = mCurrentPos - 1;
		}
		
		Log.e(TAG, "swap: " + mCurrentPos + " <> " + swapWith);
		
		int swapPos = mPosMapping[swapWith];
		mPosMapping[swapWith] = mPosMapping[mCurrentPos];
		mPosMapping[mCurrentPos] = swapPos;
		
		notifyDataSetChanged();
	}
	
	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		Log.e(TAG, "current: " + position);
		mCurrentPos = position;
		super.setPrimaryItem(container, position, object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		int mapping = getItemMapping(position);
		if(mapping < 0){
			throw new IllegalStateException("mapping not found");
		}
		return super.instantiateItem(container, mapping);
	}
	
	@Override
	public int getItemPosition(Object object)
	{
		int position = super.getItemPosition(object);
		if(position < 0){
			return position;
		}
		return mPosMapping[position];
	}
}
