package net.miz_hi.smileessence.command.user;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.async.MyExecutor;
import net.miz_hi.smileessence.command.IConfirmable;
import net.miz_hi.smileessence.core.Notifier;
import net.miz_hi.smileessence.twitter.TwitterManager;

public class UserCommandFollow extends UserCommand implements IConfirmable
{

	public UserCommandFollow(String userName)
	{
		super(userName);
	}

	@Override
	public String getName()
	{
		return "�t�H���[����";
	}

	@Override
	public void workOnUiThread()
	{
		MyExecutor.execute(new Runnable()
		{

			@Override
			public void run()
			{
				if (TwitterManager.follow(Client.getMainAccount(), userName))
				{
					Notifier.info("�t�H���[���܂���");
				}
			}
		});
	}

	@Override
	public boolean getDefaultVisibility()
	{
		return !Client.getMainAccount().getScreenName().equals(userName);
	}

}
