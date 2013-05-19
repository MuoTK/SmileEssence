package net.miz_hi.smileessence.command.status;

import twitter4j.TwitterException;
import net.miz_hi.smileessence.async.MyExecutor;
import net.miz_hi.smileessence.command.IHideable;
import net.miz_hi.smileessence.core.Notifier;
import net.miz_hi.smileessence.status.StatusModel;
import net.miz_hi.smileessence.twitter.TwitterManager;

public class StatusCommandUnfavorite extends StatusCommand implements IHideable
{

	public StatusCommandUnfavorite(StatusModel status)
	{
		super(status);
	}

	@Override
	public String getName()
	{
		return "���C�ɓ�����폜����";
	}
	
	@Override
	public void workOnUiThread()
	{
		MyExecutor.execute(new Runnable()
		{
			
			@Override
			public void run()
			{
				try
				{
					TwitterManager.getTwitter().destroyFavorite(status.statusId);
					Notifier.info("���C�ɓ�����폜���܂���");
				}
				catch (TwitterException e)
				{
					e.printStackTrace();
					Notifier.alert("���C�ɓ���̍폜�Ɏ��s���܂���");
				}
			}
		});

	}

}
