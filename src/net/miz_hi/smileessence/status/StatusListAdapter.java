package net.miz_hi.smileessence.status;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.R;
import net.miz_hi.smileessence.core.CustomListAdapter;
import net.miz_hi.smileessence.core.UiHandler;
import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.listener.StatusOnClickListener;
import net.miz_hi.smileessence.view.MainActivity;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class StatusListAdapter extends CustomListAdapter<StatusModel>
{

	public StatusListAdapter(Activity activity)
	{
		super(activity, 5000);
	}	

	@Override
	public View getView(int position, View convertedView, ViewGroup parent)
	{
		if (convertedView == null)
		{
			convertedView = getInflater().inflate(R.layout.status_layout, null);
		}
		StatusModel model = (StatusModel) getItem(position);
		convertedView = StatusViewFactory.getView(getInflater(), model, convertedView);
		if(model.isRetweet || model.isReply)
		{
			convertedView.setBackgroundColor(model.backgroundColor);
		}
		else
		{
			if(position % 2 == 0)
			{
				convertedView.setBackgroundColor(Client.getColor(R.color.White));
			}
			else
			{
				convertedView.setBackgroundColor(Client.getColor(R.color.LightGray));
			}
		}
		convertedView.setOnClickListener(new StatusOnClickListener(getActivity(), model));
		return convertedView;
	}
}
