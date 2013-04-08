package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.event.ToastManager;
import net.miz_hi.smileessence.system.TweetSystem;

public class StatusCommandAddReply extends StatusCommand
{

	public StatusCommandAddReply(StatusModel model)
	{
		super(model);
	}

	@Override
	public String getName()
	{
		return "���v���C��ɒǉ�";
	}

	@Override
	public void workOnUiThread()
	{
		TweetSystem.addReply(status.screenName);
		ToastManager.toast(status.screenName + "�����v���C��ɒǉ����܂���");
	}
}
