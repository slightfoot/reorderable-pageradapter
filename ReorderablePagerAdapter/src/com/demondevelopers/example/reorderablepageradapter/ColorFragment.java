package com.demondevelopers.example.reorderablepageradapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ColorFragment extends Fragment
{
	private static final String ARG_POS   = "argPos";
	private static final String ARG_COLOR = "argColor";
	
	
	public ColorFragment()
	{
		//
	}
	
	public static ColorFragment newInstance(int pos, int color)
	{
		Bundle args = new Bundle();
		args.putInt(ARG_POS, pos);
		args.putInt(ARG_COLOR, color);
		ColorFragment frag = new ColorFragment();
		frag.setArguments(args);
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		TextView tv = new TextView(container.getContext());
		tv.setGravity(Gravity.CENTER);
		tv.setText("Pos: #" + getArguments().getInt(ARG_POS));
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(48.0f);
		tv.setBackgroundColor(getArguments().getInt(ARG_COLOR));
		return tv;
	}
}