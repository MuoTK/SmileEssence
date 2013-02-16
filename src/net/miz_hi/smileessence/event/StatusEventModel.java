package net.miz_hi.smileessence.event;

import java.util.Date;

import net.miz_hi.smileessence.data.StatusModel;
import net.miz_hi.smileessence.data.StatusStore;
import twitter4j.Status;
import twitter4j.User;

public class StatusEventModel extends EventModel
{

	public EnumStatusEventType type;
	public StatusModel targetModel;
	
	public StatusEventModel(User source, EnumStatusEventType type, Status target)
	{
		super(source);
		this.type = type;
		this.targetModel = StatusStore.put(target);
	}

	public static enum EnumStatusEventType
	{
		REPLY("����̕ԐM"),
		RETWEET("�Ƀ��c�C�[�g���ꂽ"),
		FAVORITE("�ɂӂ��ڂ�ꂽ"),
		UNFAVORITE("�ɂ���ӂ��ڂ��ꂽ"),;

		private final String text;

		private EnumStatusEventType(String text)
		{
			this.text = text;
		}

		public String getText()
		{
			return text;
		}
	}
}
