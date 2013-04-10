package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.data.template.Templates;
import net.miz_hi.smileessence.event.ToastManager;

public class CommandAddTemplate extends MenuCommand
{

	private String text;

	public CommandAddTemplate(String text)
	{
		this.text = text;
	}

	@Override
	public String getName()
	{
		return "��^���ɒǉ�";
	}

	@Override
	public void workOnUiThread()
	{
		Templates.addTemplate(text);
		ToastManager.toast("�ǉ����܂���");
	}
}
