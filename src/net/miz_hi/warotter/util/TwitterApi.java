package net.miz_hi.warotter.util;

import net.miz_hi.warotter.Warotter;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;

public class TwitterApi
{
	private static final String MESSAGE_TWEET_SUCCESS = "���e���܂���";
	private static final String MESSAGE_TWEET_DEPLICATE = "���e���s���܂���";
	private static final String MESSAGE_TWEET_LIMIT = "�K������Ă��܂�";
	private static final String ERROR_STATUS_DUPLICATE = "Status is a duplicate.";
	private static final String ERROR_STATUS_LIMIT = "User is over daily status update limit.";

	public static String tweet(StatusUpdate st)
	{
		try
		{
			Warotter.getTwitter().updateStatus(st);
		}
		catch (TwitterException e)
		{
			int code = e.getStatusCode();
			String message = e.getErrorMessage();
			if (code == 403)
			{
				if (message.equals(ERROR_STATUS_DUPLICATE))
				{
					return MESSAGE_TWEET_DEPLICATE;
				}
				if (message.equals(ERROR_STATUS_LIMIT))
				{
					return MESSAGE_TWEET_LIMIT;
				}
			}
		}
		return MESSAGE_TWEET_SUCCESS;
	}
}
