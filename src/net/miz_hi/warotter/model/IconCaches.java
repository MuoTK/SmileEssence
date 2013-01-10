package net.miz_hi.warotter.model;

import gueei.binding.Observable;
import gueei.binding.converters.STITCH;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import net.miz_hi.warotter.util.AsyncIconGetter;
import net.miz_hi.warotter.util.StringUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import twitter4j.User;

public class IconCaches
{
	
	private static HashMap<Long, Icon> iconCache = new HashMap<Long, IconCaches.Icon>();

	public static void setIconBitmap(User user, Observable<Bitmap> observable)
	{
		String fileName = genIconFileName(user);
		File latestIconFile = Warotter.getApplicationFile(fileName);
		File cacheDir = Warotter.getApplication().getExternalCacheDir();
		File[] caches = cacheDir.listFiles();
		/*
		 * URL����A�C�R�����X�V����Ă��邩�ǂ����̊m�F
		 */
		boolean needsCacheUpdate = true;
		if(iconCache.containsKey(user.getId()))
		{
			needsCacheUpdate = !iconCache.get(user.getId()).fileName.equals(fileName);
		}
		else
		{
			needsCacheUpdate = !latestIconFile.exists();
		}
		/*
		 * �X�V���s�v
		 */
		if (!needsCacheUpdate)
		{
			/*
			 * from �������L���b�V��
			 */
			if (iconCache.containsKey(user.getId()))
			{
				Icon icon = iconCache.get(user.getId());
				observable.set(icon.use());
			}
			/*
			 * from �t�@�C���L���b�V��
			 */
			else
			{
				Options opt = new Options();
				opt.inPurgeable = true; //GC�\�ɂ���
				Bitmap bm = BitmapFactory.decodeFile(latestIconFile.getPath(), opt);
				putIconToMap(user, new Icon(bm, fileName));
				observable.set(bm);
			}
		}
		else
		{
			AsyncIconGetter async = new AsyncIconGetter(latestIconFile, observable);
			async.execute(user);
		}
	}

	private static String genIconFileName(User user)
	{
		return String.format("%1$s_%2$s", user.getId(), StringUtils.parseUrlToFileName(user.getProfileImageURL()));
	}

	public static void putIconToMap(User user, Icon icon)
	{
		if (iconCache.containsKey(user.getId()))
		{
			iconCache.remove(user.getId());
		}
		
		iconCache.put(user.getId(), icon);
		
		if(iconCache.size() > 200)
		{
			LinkedList<Icon> list = new LinkedList<IconCaches.Icon>(iconCache.values());
			Collections.sort(list);
			list.removeLast();
		}
	}
	
	public static class Icon implements Comparable<Icon>
	{
		public Bitmap bitmap;
		public String fileName;
		public int count = 0;
		
		public Icon(Bitmap bitmap, String fileName)
		{
			this.bitmap = bitmap;
			this.fileName = fileName;
		}
		
		public Bitmap use()
		{
			count++;
			return bitmap;
		}

		@Override
		public int compareTo(Icon another)
		{
			return this.count > another.count ? 1 : (this.count == another.count ? 0 : -1);
		}	
		
	}
}
