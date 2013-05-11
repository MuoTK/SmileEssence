package net.miz_hi.smileessence.command.user;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.async.MyExecutor;
import net.miz_hi.smileessence.command.IConfirmable;
import net.miz_hi.smileessence.core.Notifier;
import net.miz_hi.smileessence.twitter.TwitterManager;

public class UserCommandBlock extends UserCommand implements IConfirmable
{

	public UserCommandBlock(String username)
	{
		super(username);
	}

	@Override
	public String getName()
	{
		return "�u���b�N";
	}

	@Override
	public void workOnUiThread()
	{
		MyExecutor.execute(new Runnable()
		{
			@Override
			public void run()
			{
				if (TwitterManager.block(Client.getMainAccount(), userName))
				{
					Notifier.info(userName + "���u���b�N���܂���");
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
