package com.lifesum.assignment.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lifesum.assignment.R;
import com.lifesum.assignment.data.dto.BasePageBean;

public class SearchAdapter extends GenericAdapter {

	private List<String> mFavIds;

	public SearchAdapter(Context context) {
		super(context);
	}

	@Override
	protected void populateHolder(final BasePageBean bean, ViewHolder holder, int position) {
		holder.title.setText(bean.getTitle());
		holder.category.setText(bean.getCategory());
		holder.format.setText(bean.getFormat());
		holder.calories.setText(bean.getCalories() + "");

		if (mFavIds.contains(bean.getId()))
			holder.addToFav.setImageResource(R.drawable.bt_star_lightred);
		else
			holder.addToFav.setImageResource(R.drawable.bt_star_red);

		holder.addToFav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mFavIds.contains(bean.getId()))
					((ImageView) v).setImageResource(R.drawable.bt_star_red);
				else
					((ImageView) v).setImageResource(R.drawable.bt_star_lightred);
				mListener.onFavouriteIconClicked(bean);
			}
		});

	}

	public void setFavoritesIds(List<String> mFavIds) {
		this.mFavIds = mFavIds;
		notifyDataSetChanged();
	}

}
