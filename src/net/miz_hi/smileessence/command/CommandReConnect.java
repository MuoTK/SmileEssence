package net.miz_hi.smileessence.command;

import net.miz_hi.smileessence.system.MainSystem;

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
		MainSystem.getInstance().connectUserStream();
	}

}
