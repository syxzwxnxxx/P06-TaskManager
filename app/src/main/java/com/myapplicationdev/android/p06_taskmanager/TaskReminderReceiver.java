package com.myapplicationdev.android.p06_taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class TaskReminderReceiver extends BroadcastReceiver {

	int notifReqCode = 123;
	int notificationId = 001; // A unique ID for our notification

	@Override
	public void onReceive(Context context, Intent i) {

		int id = i.getIntExtra("id", -1);
		String name = i.getStringExtra("name");
		String desc = i.getStringExtra("desc");

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
		PendingIntent pendingIntent =
				PendingIntent.getActivity(context, 0,
						intent, PendingIntent.FLAG_UPDATE_CURRENT);

		android.support.v7.app.NotificationCompat.Action action = new
				android.support.v7.app.NotificationCompat.Action.Builder(
				R.mipmap.ic_launcher,"Task",
				pendingIntent).build();

		Intent intentreply = new Intent(context,
				ReplyActivity.class);
		PendingIntent pendingIntentReply = PendingIntent.getActivity
				(context, 0, intentreply,
						PendingIntent.FLAG_UPDATE_CURRENT);

		RemoteInput ri = new RemoteInput.Builder("status")
				.setLabel("Status report")
				.setChoices(new String[]{"Completed", "Not yet"})
				.build();

	android.support.v7.app.NotificationCompat.Action action2 = new
				android.support.v7.app.NotificationCompat.Action.Builder(
				R.mipmap.ic_launcher,
				"Reply",
				pendingIntentReply)
				.addRemoteInput(ri)
				.build();

		android.support.v7.app.NotificationCompat.WearableExtender extender = new
				android.support.v7.app.NotificationCompat.WearableExtender();
		extender.addAction(action);
		extender.addAction(action2);

		// build notification
		Notification notification = new
				android.support.v7.app.NotificationCompat.Builder(context)
		.setContentTitle(("Task"))
		.setContentText((name + " " + desc))
		.setSmallIcon((android.R.drawable.ic_dialog_info))
		.setContentIntent((pIntent))
		.setAutoCancel((true))
		.extend(extender)
		.build();

//		Notification n = builder.build();
//		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//		// You may put an ID for the first parameter if you wish
//		// to locate this notification to cancel
//		notificationManager.notify(notifReqCode, n);
//
		NotificationManagerCompat notificationManagerCompat =
				NotificationManagerCompat.from(context);
		notificationManagerCompat.notify(notificationId, notification );
	}

}
