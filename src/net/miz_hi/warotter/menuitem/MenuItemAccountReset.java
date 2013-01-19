package net.miz_hi.warotter.menuitem;

import net.miz_hi.warotter.auth.AuthentificationDB;
import net.miz_hi.warotter.core.EventHandlerActivity;
import net.miz_hi.warotter.dialog.DialogAdapter;
import net.miz_hi.warotter.model.Warotter;

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
