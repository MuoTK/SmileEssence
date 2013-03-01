package net.miz_hi.smileessence.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.R;
import net.miz_hi.smileessence.async.MyExecutor;
import net.miz_hi.smileessence.menu.MenuItemBase;
import net.miz_hi.smileessence.menu.MenuItemClose;
import net.miz_hi.smileessence.util.StringUtils;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class DialogAdapter
{
	protected Activity activity;
	protected List<MenuItemBase> list;
	protected static LayoutInflater layoutInflater;
	protected static Dialog dialog;
	protected View[] titleViews;

	public DialogAdapter(Activity activity)
	{
		this.activity = activity;
		layoutInflater = LayoutInflater.from(activity);
		list = new ArrayList<MenuItemBase>();
	}

	public DialogAdapter setMenuItems(List<? extends MenuItemBase> list)
	{
		this.list.clear();
		this.list.addAll(list);
		return this;
	}

	public List<MenuItemBase> getList()
	{
		return list;
	}

	public static Dialog getDialog()
	{
		return dialog;
	}

	public boolean isShowing()
	{
		if (dialog == null)
		{
			return false;
		}
		else
		{
			return dialog.isShowing();
		}
	}
	
	public void setTitleViews(View... views)
	{
		titleViews = views;
	}
	
	public View[] getTitleViews()
	{
		return titleViews; 
	}
	
	public void setTitle(String string)
	{
		TextView title = new TextView(activity);
		title.setTextSize(15);
		title.setTextColor(Client.getResource().getColor(R.color.White));
		title.setText(string);
		title.setPadding(10, 15, 0, 15);
		setTitleViews(title);
	}

	public abstract Dialog createMenuDialog(boolean init);
	
	protected Dialog createMenuDialog()
	{
		if(dialog != null)
		{
			dialog.dismiss();
		}
		
		dialog = new Dialog(activity);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		View view = layoutInflater.inflate(R.layout.dialog_base_layout, null);
		LinearLayout titleLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_dialogTitle);
		LinearLayout itemsLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_dialogItems);
		
		titleLinearLayout.removeAllViews();
		itemsLinearLayout.removeAllViews();
		
		for (View v : titleViews)
		{			
			if(v.getParent() != null)
			{
				((ViewGroup)v.getParent()).removeView(v);
			}
			titleLinearLayout.addView(v);
		}

		for (MenuItemBase item : list)
		{
			if (item.isVisible())
			{
				itemsLinearLayout.addView(new MenuItemView(activity, item).getView());
			}
		}
		itemsLinearLayout.addView(new MenuItemView(activity, new MenuItemClose(activity, this)).getView());
		itemsLinearLayout.setClickable(true);
		dialog.setContentView(view);
		LayoutParams lp = dialog.getWindow().getAttributes();
		DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
		lp.width = (int) (metrics.widthPixels * 0.95);
		lp.gravity = Gravity.CENTER;
		// lp.height = (int) (metrics.heightPixels * 0.9);
		return dialog;
	}

	public static void dispose()
	{
		if (dialog != null)
		{
			dialog.dismiss();
		}
	}

	public static class MenuItemView
	{
		private MenuItemBase item;
		private LayoutInflater layoutInflater;

		public MenuItemView(Activity activity, MenuItemBase item)
		{
			this.item = item;
			layoutInflater = LayoutInflater.from(activity);
		}

		public View getView()
		{
			View view = layoutInflater.inflate(R.layout.menuitem_layout, null);
			TextView textView = (TextView) view.findViewById(R.id.textView_menuItem);
			textView.setText(item.getText());
			view.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					view.setBackgroundColor(Color.rgb(30, 30, 120));
					item.run();
				}
			});
			return view;
		}

	}
}
