package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.event.ToastManager;
import android.content.Context;
import android.text.ClipboardManager;

public class StatusCommandClipboard extends StatusCommand implements IHideable
{

	private static boolean isVisible = true;

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
			ToastManager.getInstance().toast("�R�s�[���܂���");
		}
		catch (Exception e)
		{
			ToastManager.getInstance().toast("�R�s�[���s���܂���");
		}
	}

	@Override
	public boolean getIsVisible()
	{
		return isVisible;
	}

	@Override
	public void setIsVisible(boolean value)
	{
		isVisible = value;
	}
}
