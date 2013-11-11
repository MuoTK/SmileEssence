package net.miz_hi.smileessence.command.user;

import android.app.Activity;
import android.app.ProgressDialog;
import net.miz_hi.smileessence.Client;
import net.miz_hi.smileessence.command.CommandOpenUserList;
import net.miz_hi.smileessence.command.ICommand;
import net.miz_hi.smileessence.command.MenuCommand;
import net.miz_hi.smileessence.core.MyExecutor;
import net.miz_hi.smileessence.dialog.SimpleMenuDialog;
import net.miz_hi.smileessence.notification.Notificator;
import net.miz_hi.smileessence.statuslist.StatusListManager;
import net.miz_hi.smileessence.twitter.API;
import net.miz_hi.smileessence.util.UiHandler;
import twitter4j.UserList;

import java.util.ArrayList;
import java.util.List;

public class CommandShowUserLists extends MenuCommand
{

    Activity activity;

    public CommandShowUserLists(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public String getName()
    {
        return "リストのタブを追加";
    }

    @Override
    public void workOnUiThread()
    {
        final ProgressDialog pd = ProgressDialog.show(activity, null, "更新中...", true);
        MyExecutor.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    final List<UserList> lists = API.getReadableLists(Client.getMainAccount());
                    final List<ICommand> commands = new ArrayList<ICommand>();
                    for (UserList list : lists)
                    {
                        if (StatusListManager.getListTimeline(list.getId()) == null)
                        {
                            commands.add(new CommandOpenUserList(activity, list));
                        }
                    }

                    if (commands.isEmpty())
                    {
                        Notificator.alert("追加できるリストがありません");
                    }
                    else
                    {
                        final SimpleMenuDialog menu = new SimpleMenuDialog(activity, "リストを選択")
                        {
                            @Override
                            public List<ICommand> getMenuList()
                            {
                                return commands;
                            }
                        };
                        new UiHandler()
                        {

                            @Override
                            public void run()
                            {
                                menu.create().show();
                            }
                        }.post();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Notificator.alert("リストの取得に失敗しました");
                }
                pd.dismiss();
            }
        });
    }

}