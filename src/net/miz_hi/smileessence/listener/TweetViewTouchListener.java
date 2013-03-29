package net.miz_hi.smileessence.listener;

import net.miz_hi.smileessence.util.LogHelper;
import net.miz_hi.smileessence.util.UiHandler;
import net.miz_hi.smileessence.view.MainActivity;
import net.miz_hi.smileessence.view.TweetView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TweetViewTouchListener implements OnTouchListener
{

	// X�ʒu�̓��씻�f
	private float MARGIN_X = 60.0f;
	// �G�ꂽ�u�Ԃ�X�ʒu
	private float mDownX;
	// �G�ꂽ�u�Ԃ�Y�ʒu
	private float mDownY;
	// �E�ړ�
	private final static int MOVE_RIGHT = 1;
	// ���ړ�
	private final static int MOVE_LEFT = -1;

	public boolean onTouch(MotionEvent event)
	{
		int action = event.getAction();
		switch (action)
		{
			case MotionEvent.ACTION_DOWN:
			{
				actionDown(event);					
			}
			case MotionEvent.ACTION_UP:
			{
				return actionUp(event);
			}
		}
		return false;
	}
	
	private void actionDown(MotionEvent event)
	{
		// �ʒu��ۑ�
		mDownX = event.getX();
		mDownY = event.getY();
	}

	private boolean actionUp(MotionEvent event)
	{
		// ���݈ʒu�� ACTION_DOWN ���̈ʒu���r
		float moveX = mDownX - event.getX();
		float moveY = mDownY - event.getY();
		// �ړ��������͈͓��Ȃ疳��
		if (Math.abs(moveX) > MARGIN_X )
		{
			changeList(moveX > 0 ? MOVE_RIGHT : MOVE_LEFT);
			return true;
		}
		return false;
	}

	private void changeList(int i)
	{
		switch (i)
		{
			case MOVE_LEFT:
			{
				break;
			}
			case MOVE_RIGHT:
			{
				TweetView.getInstance().close();
				break;
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		return onTouch(event);
	}

}
