package net.miz_hi.smileessence.event;

public enum EnumEventType
{
	REPLY("����̕ԐM"),
	RETWEET("�Ƀ��c�C�[�g���ꂽ"),
	BLOCK("�Ƀu���b�N���ꂽ"),
	UNBLOCK("�Ƀu���b�N�������ꂽ"),
	DIRECT_MESSAGE("����DM���󂯎����"),
	FAVORITE("�ɂӂ��ڂ�ꂽ"),
	UNFAVORITE("�ɂ���ӂ��ڂ��ꂽ"),
	FOLLOW("�Ƀt�H���[���ꂽ"), ;

	private final String text;

	private EnumEventType(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
}
