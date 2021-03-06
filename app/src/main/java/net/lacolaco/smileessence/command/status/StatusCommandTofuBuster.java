/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2014 lacolaco.net
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.lacolaco.smileessence.command.status;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

import net.lacolaco.smileessence.R;
import net.lacolaco.smileessence.notification.Notificator;

import twitter4j.Status;

public class StatusCommandTofuBuster extends StatusCommand {

    // --------------------------- CONSTRUCTORS ---------------------------

    public StatusCommandTofuBuster(Activity activity, Status status) {
        super(R.id.key_command_status_tofubuster, activity, status);
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    @Override
    public String getText() {
        return getActivity().getString(R.string.command_status_tofubuster);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // -------------------------- OTHER METHODS --------------------------

    @Override
    public boolean execute() {
        String ACTION_SHOW_TEXT = "com.product.kanzmrsw.tofubuster.ACTION_SHOW_TEXT";
        String text = getOriginalStatus().getText();
        Intent i = new Intent(ACTION_SHOW_TEXT);
        i.putExtra(Intent.EXTRA_TEXT, text);
        i.putExtra(Intent.EXTRA_SUBJECT, getActivity().getString(R.string.app_name));
        i.putExtra("isCopyEnabled", true);
        try {
            getActivity().startActivity(i);
        } catch (ActivityNotFoundException e) {
            new Notificator(getActivity(), R.string.notice_tofubuster_not_found).publish();
        }
        return true;
    }
}
