package net.miz_hi.smileessence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

/**
 * �a�����[���X�ϊ����C�u����
 * @author flour
 * @version 2.2
 * @arrange laco0416
 */
public class Morse
{

	private static HashMap<String, String> jaMc;
	private static HashMap<String, String> mcJa;

	/**
	 * �^����ꂽ�����񂪃��[���X�����܂ނ��ǂ�����Ԃ�
	 * @param mc ���肷�镶����
	 * @return ���[���X���܂ނȂ�true,�����łȂ����false
	 */
	public static boolean isMorse(String mc)
	{
		Pattern pattern = Pattern.compile("[�|�E]+");
		Matcher matcher = pattern.matcher(mc);
		ArrayList<String> list = new ArrayList<String>();
		while(matcher.find())
		{
			list.add(matcher.group());
		}
		if(list.size() <= 1)
		{
			return false;
		}
		else
		{
			for(String s: list)
			{
				if(!s.equals("�E�E�E") && !s.equals("�E�E") && !s.equals("�E"))
				{
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * �a�����[���X���J�^�J�i�E�����ɕ�������	 *
	 * @param str ����������������
	 * @return �����������u�����ꂽ������
	 */
	public static String mcToJa(String str)
	{
		String[] strArr = toRightMorse(str).split(" ");
		StringBuilder sb = new StringBuilder();
		for (String tok : strArr)
		{
			sb.append(mcJa.containsKey(tok) ? mcJa.get(tok) : tok);
		}
		return sb.toString();
	}

	/**
	 * �Ђ炪�ȁE�J�^�J�i�E���������[���X���ɕϊ�����
	 * @param str �ϊ�������������
	 * @return �ϊ��������u�����ꂽ������
	 */
	public static String jaToMc(String str)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++)
		{
			String tok = String.valueOf(str.charAt(i));
			if(jaMc.containsKey(tok))
			{
				sb.append(jaMc.get(tok));
				sb.append(" ");
			}
			else
			{
				sb.append(tok);
			}
		}
		return sb.toString().trim();
	}


	private static String toRightMorse(String str)
	{
		str = str.replace("�]", "�|").replace("�@", " ").trim();
		Pattern pattern = Pattern.compile("[^�E�| ][�E�|]");
		StringBuilder sb = new StringBuilder(str);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find())
		{
			int i = matcher.start();
			sb.insert(i + 1, " ");
			matcher.reset(sb);
		}
		pattern = Pattern.compile("[�E�|][^�E�| ]");
		matcher = pattern.matcher(sb);
		while(matcher.find())
		{
			int i = matcher.start();
			sb.insert(i + 1, " ");
			matcher.reset(sb);
		}
		return sb.toString();
	}

	static
	{
		jaMc = new HashMap<String, String>();
		mcJa = new HashMap<String, String>();

		String[][] ja2 = {{"�E�|", "�C"}, {"�E�|�E�|", "��"}, {"�|�E�E�E", "�n"}, {"�|�E�|�E", "�j"}, {"�|�E�E", "�z"},
				{"�E", "�w"}, {"�E�E�|�E�E", "�g"}, {"�E�E�|�E", "�`"}, {"�|�|�E", "��"}, {"�E�E�E�E", "�k"},
				{"�|�E�|�|�E", "��"}, {"�E�|�|�|", "��"}, {"�|�E�|", "��"}, {"�E�|�E�E", "�J"}, {"�|�|", "��"},
				{"�|�E", "�^"}, {"�|�|�|", "��"}, {"�|�|�|�E", "�\"}, {"�E�|�|�E", "�c"}, {"�|�|�E�|", "�l"},
				{"�E�|�E", "�i"}, {"�E�E�E", "��"}, {"�|", "��"}, {"�E�E�|", "�E"}, {"�E�|�E�E�|", "��"},
				{"�E�E�|�|", "�m"}, {"�E�|�E�E�E", "�I"}, {"�E�E�E�|", "�N"}, {"�E�|�|", "��"}, {"�|�E�E�|", "�}"},
				{"�|�E�|�|", "�P"}, {"�|�|�E�E", "�t"}, {"�|�|�|�|", "�R"}, {"�|�E�|�|�|", "�G"}, {"�E�|�E�|�|", "�e"},
				{"�|�|�E�|�|", "�A"}, {"�|�E�|�E�|", "�T"}, {"�|�E�|�E�E", "�L"}, {"�|�E�E�|�|", "��"}, {"�|�E�E�E�|", "��"},
				{"�E�E�|�E�|", "�~"}, {"�|�|�E�|�E", "�V"}, {"�E�|�|�E�E", "��"}, {"�|�|�E�E�|", "�q"}, {"�|�E�E�|�E", "��"},
				{"�E�|�|�|�E", "�Z"}, {"�|�|�|�E�|", "�X"}, {"�E�|�E�|�E", "��"}, {"�E�E", "�J"}, {"�E�E�|�|�E", "�K"},
				{"�E�|�|�E�|", "�["}, {"�E�|�E�|�E�|", "�A"}, {"�|�E�|�|�E�|", "�i"}, {"�E�|�E�E�|�E", "�j"}, {"�E�|�|�|�|", "1"},
				{"�E�E�|�|�|", "2"}, {"�E�E�E�|�|", "3"}, {"�E�E�E�E�|", "4"}, {"�E�E�E�E�E", "5"}, {"�|�E�E�E�E", "6"},
				{"�|�|�E�E�E", "7"}, {"�|�|�|�E�E", "8"}, {"�|�|�|�|�E", "9"}, {"�|�|�|�|�|", "0"}, {"", ""}};
		for(String[] pr : ja2)
		{
			mcJa.put(pr[0], pr[1]);
			jaMc.put(pr[1], pr[0]);
		}

		String[][] ja1 = {{"��", "�E�|"}, {"�B", "�E�|"}, {"��", "�E�|"}, {"��", "�E�|�E�|"}, {"��", "�|�E�E�E"},
				{"��", "�|�E�|�E"}, {"��", "�|�E�E"}, {"��", "�E"}, {"��", "�E�E�|�E�E"}, {"��", "�E�E�|�E"},
				{"��", "�|�|�E"}, {"��", "�E�E�E�E"}, {"��", "�|�E�|�|�E"}, {"��", "�E�|�|�|"}, {"��", "�|�E�|"},
				{"��", "�|�E�|"}, {"��", "�|�E�|"}, {"��", "�E�|�E�E"}, {"��", "�E�|�E�E"}, {"��", "�|�|"},
				{"��", "�|�|"}, {"��", "�|�|"}, {"��", "�|�E"}, {"��", "�|�|�|"}, {"��", "�|�|�|�E"},
				{"��", "�E�|�|�E"}, {"�b", "�E�|�|�E"}, {"��", "�E�|�|�E"}, {"��", "�|�|�E�|"}, {"��", "�E�|�E"},
				{"��", "�E�E�E"}, {"��", "�|"}, {"��", "�E�E�|"}, {"�D", "�E�E�|"}, {"��", "�E�E�|"},
				{"��", "�E�|�E�E�|"}, {"��", "�E�E�|�|"}, {"��", "�E�|�E�E�E"}, {"�H", "�E�|�E�E�E"}, {"��", "�E�|�E�E�E"},
				{"��", "�E�E�E�|"}, {"��", "�E�|�|"}, {"��", "�E�|�|"}, {"��", "�E�|�|"}, {"��", "�|�E�E�|"},
				{"��", "�|�E�|�|"}, {"��", "�|�E�|�|"}, {"��", "�|�|�E�E"}, {"��", "�|�|�|�|"}, {"��", "�|�E�|�|�|"},
				{"�F", "�|�E�|�|�|"}, {"��", "�|�E�|�|�|"}, {"��", "�E�|�E�|�|"}, {"��", "�|�|�E�|�|"}, {"�@", "�|�|�E�|�|"},
				{"��", "�|�|�E�|�|"}, {"��", "�|�E�|�E�|"}, {"��", "�|�E�|�E�E"}, {"��", "�|�E�E�|�|"}, {"��", "�|�E�E�|�|"},
				{"��", "�|�E�E�|�|"}, {"��", "�|�E�E�E�|"}, {"��", "�E�E�|�E�|"}, {"��", "�|�|�E�|�E"}, {"��", "�E�|�|�E�E"},
				{"��", "�|�|�E�E�|"}, {"��", "�|�E�E�|�E"}, {"��", "�E�|�|�|�E"}, {"��", "�|�|�|�E�|"}, {"��", "�E�|�E�|�E"},
				{"�K", "�E�|�E�E �E�E"}, {"��", "�E�|�E�E �E�E"}, {"�M", "�|�E�|�E�E �E�E"}, {"��", "�|�E�|�E�E �E�E"}, {"�O", "�E�E�E�| �E�E"},
				{"��", "�E�E�E�| �E�E"}, {"�Q", "�|�E�|�| �E�E"}, {"��", "�|�E�|�| �E�E"}, {"�S", "�|�|�|�| �E�E"}, {"��", "�|�|�|�| �E�E"},
				{"�U", "�|�E�|�E�| �E�E"}, {"��", "�|�E�|�E�| �E�E"}, {"�W", "�|�|�E�|�E �E�E"}, {"��", "�|�|�E�|�E �E�E"}, {"�Y", "�|�|�|�E�| �E�E"},
				{"��", "�|�|�|�E�| �E�E"}, {"�[", "�E�|�|�|�E �E�E"}, {"��", "�E�|�|�|�E �E�E"}, {"�]", "�|�|�|�E �E�E"}, {"��", "�|�|�|�E �E�E"},
				{"�_", "�|�E �E�E"}, {"��", "�|�E �E�E"}, {"�a", "�E�E�|�E �E�E"}, {"��", "�E�E�|�E �E�E"}, {"�d", "�E�|�|�E �E�E"},
				{"��", "�E�|�|�E �E�E"}, {"�f", "�E�|�E�|�| �E�E"}, {"��", "�E�|�E�|�| �E�E"}, {"�h", "�E�E�|�E�E �E�E"}, {"��", "�E�E�|�E�E �E�E"},
				{"�o", "�|�E�E�E �E�E"}, {"��", "�|�E�E�E �E�E"}, {"�r", "�|�|�E�E�| �E�E"}, {"��", "�|�|�E�E�| �E�E"}, {"�u", "�|�|�E�E �E�E"},
				{"��", "�|�|�E�E �E�E"}, {"�x", "�E �E�E"}, {"��", "�E �E�E"}, {"�{", "�|�E�E �E�E"}, {"��", "�|�E�E �E�E"},
				{"�p", "�|�E�E�E �E�E�|�|�E"}, {"��", "�|�E�E�E �E�E�|�|�E"}, {"�s", "�|�|�E�E�| �E�E�|�|�E"}, {"��", "�|�|�E�E�| �E�E�|�|�E"}, {"�v", "�|�|�E�E �E�E�|�|�E"},
				{"��", "�|�|�E�E �E�E�|�|�E"}, {"�y", "�E �E�E�|�|�E"}, {"��", "�E �E�E�|�|�E"}, {"�|", "�|�E�E �E�E�|�|�E"}, {"��", "�|�E�E �E�E�|�|�E"},
				{"��", "�E�E�| �E�E"}};
		for(String[] to : ja1) jaMc.put(to[0], to[1]);
	}

}
