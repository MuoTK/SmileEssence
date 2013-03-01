package net.miz_hi.smileessence.menu;

import net.miz_hi.smileessence.dialog.DialogAdapter;
import android.app.Activity;

public abstract class UserMenuItemBase extends MenuItemBase
{
	protected String userName;

	public UserMenuItemBase(Activity activity, DialogAdapter adapter, String userName)
	{
		super(activity, adapter);
		this.userName = userName;
	}

	@Override
	public abstract boolean isVisible();
}