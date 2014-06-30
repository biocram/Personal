package com.lifesum.assignment.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lifesum.assignment.R;
import com.lifesum.assignment.data.dto.BasePageBean;

public class FavouritesAdapter extends GenericAdapter {
	
	private List<String> mFavIds;

	public FavouritesAdapter(Context context) {
		super(context);
	}

	@Override
	protected void populateHolder(final BasePageBean bean, ViewHolder holder,
			int position) {
		holder.title.setText(bean.getTitle());
		holder.category.setText(bean.getCategory());
		holder.format.setText(bean.getFormat());
		holder.calories.setText(bean.getCalories()+"");
		
		holder.addToFav.setImageResource(R.drawable.bt_star_lightred);

		holder.addToFav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mListener.onFavouriteIconClicked(bean);
			}
		});

	}
	
}
