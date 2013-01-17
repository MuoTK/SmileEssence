package net.miz_hi.warotter.viewmodel;

import gueei.binding.Command;
import gueei.binding.collections.ArrayListObservable;
import gueei.binding.observables.ObjectObservable;
import gueei.binding.observables.StringObservable;
import net.miz_hi.warotter.core.EventHandlerActivity;
import net.miz_hi.warotter.core.ViewModel;
import net.miz_hi.warotter.model.AuthentificationDB;
import net.miz_hi.warotter.model.Warotter;
import android.app.Activity;
import android.view.View;

public class MenuViewModel extends ViewModel
{
	public StringObservable title = new StringObservable("���j���[");
	public ArrayListObservable<MenuItemViewModel> items = new ArrayListObservable<MenuItemViewModel>(MenuItemViewModel.class);
	public ObjectObservable clickedItem = new ObjectObservable();

	public MenuViewModel()
	{
	}

	private void initialize(Activity activity)
	{
		items.add(new MenuItemViewModel("�ݒ�"));
		items.add(new MenuItemViewModel("�F�؏��̃��Z�b�g").setCommand(new Runnable()
		{

			@Override
			public void run()
			{
				AuthentificationDB.instance().deleteAll();
				Warotter.setMainAccount(null);
				toast("�S�Ă̔F�؏������Z�b�g���܂����B�ċN�����Ă�������");
			}
		}));
	}

	public Command onItemClicked = new Command()
	{

		@Override
		public void Invoke(View arg0, Object... arg1)
		{
			if (clickedItem != null && clickedItem.get() instanceof MenuItemViewModel)
			{
				((MenuItemViewModel) clickedItem.get()).run();
			}

		}
	};

	@Override
	public void onActivityCreated(EventHandlerActivity activity)
	{
		initialize(activity);		
	}

	@Override
	public void onActivityDestroy(EventHandlerActivity activity)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onEvent(String eventName, EventHandlerActivity activity)
	{
		return false;		
	}
}
