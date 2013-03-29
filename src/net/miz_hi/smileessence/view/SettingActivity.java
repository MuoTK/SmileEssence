package net.miz_hi.smileessence.view;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.R;
import net.miz_hi.smileessence.auth.AuthentificationDB;
import net.miz_hi.smileessence.dialog.SeekBarDialogHelper;
import net.miz_hi.smileessence.dialog.YesNoDialogHelper;
import net.miz_hi.smileessence.preference.EnumPreferenceKey;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class SettingActivity extends PreferenceActivity
{

	private static final String T4J_URL = "http://twitter4j.org/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

		Preference textSize = findPreference(getResources().getString(R.string.key_setting_textSize));
		textSize.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			@Override
			public boolean onPreferenceClick(Preference preference)
			{
				final SeekBarDialogHelper helper = new SeekBarDialogHelper(SettingActivity.this, "�e�L�X�g�T�C�Y");
				helper.setSeekBarMax(16);
				helper.setSeekBarStart(Client.getTextSize() - 8);
				helper.setLevelCorrect(8);
				helper.setText("�f�t�H���g = 10");
				helper.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						Client.putPreferenceValue(EnumPreferenceKey.TEXT_SIZE, helper.getProgress() + 8);
						Client.loadPreferences();
					}
				});
				helper.createSeekBarDialog().show();
				return true;
			}
		});
		
		Preference t4j = findPreference(getResources().getString(R.string.key_setting_t4j));
		t4j.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			public boolean onPreferenceClick(Preference preference)
			{
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(T4J_URL)));
				return true;
			}
		});
		
		Preference deleteAccounts = findPreference(getResources().getString(R.string.key_setting_delete_accounts));
		deleteAccounts.setOnPreferenceClickListener(new OnPreferenceClickListener()
		{
			public boolean onPreferenceClick(Preference preference)
			{
				YesNoDialogHelper helper = new YesNoDialogHelper(SettingActivity.this, "�{���Ƀ��Z�b�g���܂����H");
				helper.setOnClickListener(new OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						switch(which)
						{
							case DialogInterface.BUTTON_POSITIVE:
							{
								Toast.makeText(SettingActivity.this, "�S�Ă̔F�؏������Z�b�g���܂��B�ċN�����Ă�������", Toast.LENGTH_SHORT).show();
								AuthentificationDB.instance().deleteAll();
								finish();
								MainActivity.getInstance().finish();
								break;
							}
							default:
							{
								dialog.dismiss();
							}
						}

					}
				});
				helper.createYesNoAlert().show();
				return true;
			}
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

}
