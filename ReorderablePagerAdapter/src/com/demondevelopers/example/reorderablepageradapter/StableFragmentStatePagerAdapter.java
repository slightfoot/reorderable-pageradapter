package com.demondevelopers.example.rerderablepageradapter;

import java.util.HashMap;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;


public abstract class StableFragmentStatePagerAdapter extends FragmentStatePagerAdapter
{
	//private static final String TAG = StableFragmentStatePagerAdapter.class.getSimpleName();
	private HashMap<Object, Integer> mStablePositions;
	
	
	public StableFragmentStatePagerAdapter(FragmentManager fm)
	{
		super(fm);
		mStablePositions = new HashMap<Object, Integer>(getCount());
	}
	
	/**
	 * Override and transpose the return value to move pages around
	 * Note: remember to test for not equal to POSITION_NONE
	 */
	@Override
	public int getItemPosition(Object object)
	{
		Integer position = mStablePositions.get(object);
		if(position == null){
			return POSITION_NONE;
		}
		return position;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{
		Object object = super.instantiateItem(container, position);
		mStablePositions.put(object, Integer.valueOf(position));
		return object;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int mapping, Object object)
	{
		Integer position = mStablePositions.get(object);
		if(position == null){
			throw new IllegalStateException("stable position not found");
		}
		super.destroyItem(container, position, object);
	}
}
