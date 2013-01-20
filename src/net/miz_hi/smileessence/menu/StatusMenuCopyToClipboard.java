package net.miz_hi.smileessence.menu;

import android.text.ClipboardManager;
import net.miz_hi.smileessence.core.EventHandlerActivity;
import net.miz_hi.smileessence.dialog.DialogAdapter;
import net.miz_hi.smileessence.status.StatusModel;

public class StatusMenuCopyToClipboard extends StatusMenuItemBase
{

	public StatusMenuCopyToClipboard(EventHandlerActivity activity, DialogAdapter adapter, StatusModel model)
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
			ClipboardManager manager = (ClipboardManager)activity.getSystemService(activity.CLIPBOARD_SERVICE);
			manager.setText(model.text);
			toast("�R�s�[���܂���");
		}
		catch(Exception e)
		{
			toast("�R�s�[���s���܂���");
		}
	}
}
