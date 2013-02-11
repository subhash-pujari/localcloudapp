package com.evoapps.cloudapp.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.evoapps.cloudapp.R;
import com.evoapps.cloudapp.datastructure.FileInfo;
import com.evoapps.cloudapp.engine.FileUtility;

public class FileListItemAdapter extends BaseAdapter{

	Activity mContext;
	ArrayList<FileInfo> list;
	private static LayoutInflater inflater = null;
	String TAG = "FileListItemAdapter";
	
	public FileListItemAdapter(Activity mContext, ArrayList<FileInfo> filelist) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.list = filelist;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		
		if(arg1 == null){
			vi = inflater.inflate(R.layout.listitem,null);
		}
		
		TextView name= (TextView)vi.findViewById(R.id.name);
		TextView size= (TextView)vi.findViewById(R.id.size);
		TextView created= (TextView)vi.findViewById(R.id.created);
		TextView available= (TextView)vi.findViewById(R.id.available);
		
		
		
		FileInfo file = list.get(arg0);
		name.setText(file.getName());
		size.setText(FileUtility.getSize(file.getSize()));
		created.setText(FileUtility.getDate(file.getModificationTime()));
		available.setText(String.valueOf(file.isAvailable()));
		Log.e(TAG, "filename>>"+name.getText().toString()+"filesize"+size.getText().toString()+"created date"+
		created.getText().toString()+"available"+available.getText().toString());
		
		return vi;
	}

}
