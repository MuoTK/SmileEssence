package net.miz_hi.smileessence.menu;

import android.app.Activity;
import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.dialog.DialogAdapter;

public class MenuItemAccountReset extends MenuItemBase
{

	public MenuItemAccountReset(Activity activity, DialogAdapter factory)
	{
		super(activity, factory);
	}

	@Override
	public String getText()
	{
		return "�F�؏��̃��Z�b�g";
	}

	@Override
	public void work()
	{
		toast("�S�Ă̔F�؏������Z�b�g���܂��B�ċN�����Ă�������");
		AuthentificationDB.instance().deleteAll();
		_activity.finish();
	}

}
