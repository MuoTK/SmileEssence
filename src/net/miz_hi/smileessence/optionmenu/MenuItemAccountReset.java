package net.miz_hi.smileessence.optionmenu;

import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.core.EventHandlerActivity;
import net.miz_hi.smileessence.dialog.DialogAdapter;

public class MenuItemAccountReset extends MenuItemBase
{

	public MenuItemAccountReset(EventHandlerActivity activity, DialogAdapter factory)
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
		activity.finish();
	}

}
