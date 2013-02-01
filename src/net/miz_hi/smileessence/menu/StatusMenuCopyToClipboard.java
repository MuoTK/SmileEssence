package net.miz_hi.smileessence.menu;

import android.app.Activity;
import android.content.Context;
import android.text.ClipboardManager;
import net.miz_hi.smileessence.core.EventHandlerActivity;
import net.miz_hi.smileessence.dialog.DialogAdapter;
import net.miz_hi.smileessence.status.StatusModel;

public class StatusMenuCopyToClipboard extends StatusMenuItemBase
{

	public StatusMenuCopyToClipboard(Activity activity, DialogAdapter adapter, StatusModel model)
	{
		super(activity, adapter, model);
	}

	@Override
	public boolean isVisible()
	{
		return true;
	}

	@Override
	public String getText()
	{
		return "�{�����N���b�v�{�[�h�փR�s�[";
	}

	@Override
	public void work()
	{
		try
		{
			ClipboardManager manager = (ClipboardManager)_activity.getSystemService(Context.CLIPBOARD_SERVICE);
			manager.setText(model.text);
			toast("�R�s�[���܂���");
		}
		catch(Exception e)
		{
			toast("�R�s�[���s���܂���");
		}
	}
}
