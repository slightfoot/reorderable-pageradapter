package com.demondevelopers.example.reorderablepageradapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;


public class MainActivity extends FragmentActivity
{
	private static final int[] COLORS = {
		Color.MAGENTA, Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final SwappingPagerAdapter swapAdapter = new SwappingPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public Fragment getItem(int position)
			{
				return ColorFragment.newInstance(position, COLORS[position]);
			}
			
			@Override
			public int getCount()
			{
				return COLORS.length;
			}
		};
		
		ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager);
		viewPager.setAdapter(swapAdapter);
		
		findViewById(R.id.swap_pages).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				swapAdapter.swap();
			}
		});
	}
}
