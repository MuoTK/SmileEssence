package net.miz_hi.smileessence.menu;

import net.miz_hi.smileessence.activity.MainActivity;
import net.miz_hi.smileessence.dialog.DialogAdapter;
import android.app.Activity;

public class MenuItemReport extends MenuItemBase
{

	public MenuItemReport(Activity activity, DialogAdapter adapter)
	{
		super(activity, adapter);
	}

	@Override
	public String getText()
	{
		return "��҂փ��|�[�g�𑗂�";
	}

	@Override
	public void work()
	{
		MainActivity.getInstance().openTweetViewToTweet("#SmileEssence @laco0416 ");
	}

}
