package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.view.MainActivity;

public class CommandReConnect extends MenuCommand
{

	public CommandReConnect()
	{
	}

	@Override
	public String getName()
	{
		return "�Đڑ�";
	}

	@Override
	public void workOnUiThread()
	{
		MainActivity.getInstance().connectUserStream();
	}

}
