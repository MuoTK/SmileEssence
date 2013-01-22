package net.miz_hi.smileessence.viewmodel;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.R;
import net.miz_hi.smileessence.async.AsyncTweetTask;
import net.miz_hi.smileessence.core.EventHandlerActivity;
import net.miz_hi.smileessence.core.ViewModel;
import net.miz_hi.smileessence.util.StringUtils;
import net.miz_hi.smileessence.view.MainActivity;
import twitter4j.StatusUpdate;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.slidingmenu.lib.SlidingMenu;

public class TweetViewModel extends ViewModel
{
	private static TweetViewModel instance;
	public String text;
	public long inReplyTo;
	
	public static TweetViewModel singleton()
	{
		if(instance == null)
		{
			instance = new TweetViewModel();
		}
		return instance;
	}
	
	private TweetViewModel()
	{
		text = "";
		inReplyTo = -1;
	}

	
	@Override
	public void onActivityCreated(EventHandlerActivity activity)
	{
	}

	@Override
	public void onActivityDestroy(EventHandlerActivity activity)
	{
	}
	
	public void init(EventHandlerActivity activity, SlidingMenu menu)
	{
		final TextView viewCount = (TextView)menu.findViewById(R.id.textView_count);
		final EditText viewEdit = (EditText)menu.findViewById(R.id.editText_tweet);
		final ImageButton viewSubmit = (ImageButton)menu.findViewById(R.id.imageButton_submit);
		final ImageButton viewWarota = (ImageButton)menu.findViewById(R.id.imageButton_warota);
		final ImageButton viewMorse = (ImageButton)menu.findViewById(R.id.imageButton_morse);
		final ImageButton viewTemplate = (ImageButton)menu.findViewById(R.id.imageButton_template);
		final ImageButton viewReplace = (ImageButton)menu.findViewById(R.id.imageButton_replace);
		final ImageButton viewHashtag = (ImageButton)menu.findViewById(R.id.imageButton_hashtag);
		
		viewCount.setText("140");
		viewEdit.addTextChangedListener(new TextWatcher()
		{
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				viewCount.setText(String.valueOf(140 - s.length()));
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			
			@Override
			public void afterTextChanged(Editable s){}
		});
		
		viewSubmit.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				submit(viewEdit.getText().toString());
				viewEdit.setText("");	
				messenger.raise("toggle", null);		
			}
		});
		
		viewWarota.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				int cursor = viewEdit.getSelectionEnd();
				StringBuilder sb = new StringBuilder(viewEdit.getText().toString());
				sb.insert(cursor, "�����^��");
				viewEdit.setText(sb.toString());
				cursor = cursor + sb.length();
				if(cursor > viewEdit.getText().length())
				{
					cursor = viewEdit.getText().length();
				}
				viewEdit.setSelection(cursor);
			}
		});
	}
	
	public void onOpenSlidingMenu(SlidingMenu menu)
	{
		EditText viewEdit = (EditText)menu.findViewById(R.id.editText_tweet);
		if(!StringUtils.isNullOrEmpty(text))
		{				
			viewEdit.setText(text);
			text = "";
		}
	}

	public void onCloseSlidingMenu(SlidingMenu slidingMenu)
	{
		EditText viewEdit = (EditText)slidingMenu.findViewById(R.id.editText_tweet);
		InputMethodManager imm = (InputMethodManager) Client.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(viewEdit.getWindowToken(), 0);
		if(StringUtils.isNullOrEmpty(viewEdit.getText().toString()))
		{
			text = viewEdit.getText().toString();
		}
	}

	@Override
	public boolean onEvent(String eventName, EventHandlerActivity activity)
	{
		MainActivity mainActivity = (MainActivity)activity;
		
		return false;		
	}

	private void submit(String text)
	{
		if (StringUtils.isNullOrEmpty(text))
		{
			toast("���͂��Ă�������");
		}
		else
		{
			StatusUpdate status = new StatusUpdate(text);
			if(inReplyTo >= 0)
			{
				status.setInReplyToStatusId(inReplyTo);
				inReplyTo = -1;
			}
			AsyncTweetTask.addTask(new AsyncTweetTask(status, this));
		}
	}
}