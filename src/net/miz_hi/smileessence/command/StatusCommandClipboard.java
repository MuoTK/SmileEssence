package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.event.ToastManager;
import android.content.Context;
import android.text.ClipboardManager;

public class StatusCommandClipboard extends StatusCommand implements IHideable
{

	public StatusCommandClipboard(StatusModel model)
	{
		super(model);
	}

	@Override
	public String getName()
	{
		return "�{�����N���b�v�{�[�h�փR�s�[";
	}

	@Override
	public void workOnUiThread()
	{
		try
		{
			ClipboardManager manager = (ClipboardManager) Client.getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
			manager.setText(status.text);
			ToastManager.show("�R�s�[���܂���");
		}
		catch (Exception e)
		{
			ToastManager.show("�R�s�[���s���܂���");
		}
	}

}
