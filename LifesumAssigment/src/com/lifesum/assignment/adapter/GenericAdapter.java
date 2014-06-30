package com.lifesum.assignment.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifesum.assignment.R;
import com.lifesum.assignment.data.dto.BasePageBean;
import com.lifesum.assignment.fragment.OnFavouriteListener;

public abstract class GenericAdapter extends BaseAdapter {

	private List<BasePageBean> items;
	private LayoutInflater mInflater;

	protected OnFavouriteListener mListener;

	public GenericAdapter(Context context) {
		items = new ArrayList<BasePageBean>();
		mInflater = LayoutInflater.from(context);
	}

	public void updateItem(List<BasePageBean> newItems) {
		items.clear();
		items.addAll(newItems);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public BasePageBean getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflateView(position, convertView);
		ViewHolder holder = (ViewHolder) view.getTag();
		BasePageBean item = getItem(position);
		populateHolder(item, holder, position);
		return view;
	}

	private View inflateView(int position, View convertView) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater
					.inflate(R.layout.listview_search_item, null);
			initializeHolder(holder, convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	private void initializeHolder(ViewHolder holder, View convertView) {
		holder.title = (TextView) convertView.findViewById(R.id.title);
		holder.category = (TextView) convertView.findViewById(R.id.category);
		holder.format = (TextView) convertView.findViewById(R.id.format);
		holder.calories = (TextView) convertView.findViewById(R.id.calories);
		holder.addToFav = (ImageView) convertView
				.findViewById(R.id.addToFavourites);
	}

	protected abstract void populateHolder(final BasePageBean bean,
			ViewHolder holder, int position);

	static class ViewHolder {
		public TextView title, category, format, calories;
		public ImageView addToFav;

	}

	public void setOnFavouriteListener(OnFavouriteListener listener) {
		mListener = listener;
	}

}
