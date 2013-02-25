package net.miz_hi.smileessence.menu;

import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.dialog.DialogAdapter;
import net.miz_hi.smileessence.dialog.YesNoDialogHelper;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class MenuItemResetAccount extends MenuItemBase
{

	public MenuItemResetAccount(Activity activity, DialogAdapter factory)
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
		YesNoDialogHelper helper = new YesNoDialogHelper(activity, "�{���Ƀ��Z�b�g���܂����H");
		helper.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch(which)
				{
					case DialogInterface.BUTTON_POSITIVE:
					{
						Toast.makeText(activity, "�S�Ă̔F�؏������Z�b�g���܂��B�ċN�����Ă�������", Toast.LENGTH_SHORT).show();
						AuthentificationDB.instance().deleteAll();
						activity.finish();
						break;
					}
					default:
					{
						dialog.dismiss();
					}
				}

			}
		});
		helper.createYesNoAlert().show();
	}

}
