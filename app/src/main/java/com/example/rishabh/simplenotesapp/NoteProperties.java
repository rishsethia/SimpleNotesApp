package com.example.rishabh.simplenotesapp;

import android.content.Context;

/**
 * Created by Rishabh on 1/26/17.
 */
public class NoteProperties {
    Context mContext;
    String noteText;
    String noteTitle;
    long timeInMillis;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public NoteProperties(Context mContext, String noteText, String noteTitle, long timeInMillis) {
        this.mContext = mContext;
        this.noteText = noteText;
        this.noteTitle = noteTitle;
        this.timeInMillis = timeInMillis;

    }
}
