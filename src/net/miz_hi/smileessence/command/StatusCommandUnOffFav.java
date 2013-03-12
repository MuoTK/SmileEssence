package net.miz_hi.smileessence.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import twitter4j.StatusUpdate;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.async.AsyncFavoriteTask;
import net.miz_hi.smileessence.async.AsyncTweetTask;
import net.miz_hi.smileessence.async.MyExecutor;
import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.event.ToastManager;
import net.miz_hi.smileessence.util.TwitterManager;

public class StatusCommandUnOffFav extends StatusCommand implements IHideable
{

	public StatusCommandUnOffFav(StatusModel status)
	{
		super(status);
	}

	@Override
	public String getName()
	{
		return "������ӂ���";
	}

	@Override
	public void workOnUiThread()
	{
		String str = "@" + status.user.screenName + " ����";
		StatusUpdate update = new StatusUpdate(str);
		update.setInReplyToStatusId(status.statusId);
		MyExecutor.submit(new AsyncFavoriteTask(status.statusId));
		final Future<Boolean> f = MyExecutor.submit(new AsyncTweetTask(update));
		MyExecutor.execute(new Runnable()
		{
			
			@Override
			public void run()
			{
				
				try
				{
					if(f.get())
					{
						ToastManager.getInstance().toast(TwitterManager.MESSAGE_FAVORITE_SUCCESS);
					}
					else
					{
						ToastManager.getInstance().toast(TwitterManager.MESSAGE_FAVORITE_DEPLICATE);
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	

	@Override
	public boolean getDefaultVisibility()
	{
		return Client.getPermission().canWarotaRT();
	}

}
