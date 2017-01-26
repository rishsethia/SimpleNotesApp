package com.example.rishabh.simplenotesapp.database;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Rishabh on 1/26/17.
 */


@ContentProvider(authority = NotesProvider.AUTHORITY, database = NotesDatabase.class)
public class NotesProvider {

    public static final String AUTHORITY = "com.example.rishabh.simplenotesapp.database.NotesProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String NOTES = "notes";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = NotesDatabase.NOTES)
    public static class Notes {
        @ContentUri(
                path = Path.NOTES,
                type = "vnd.android.cursor.dir/quote",
                defaultSort = NotesColumns.TIME_IN_MILLIS + " ASC"
        )
        public static final Uri CONTENT_URI = buildUri(Path.NOTES);

        @InexactContentUri(
                name = "NOTE_ID",
                path = Path.NOTES + "/#",
                type = "vnd.android.cursor.item/NOTES",
                whereColumn = NotesColumns._ID,
                pathSegment = 1
        )
        public static Uri withId(int ID) {
            return buildUri(Path.NOTES, ID + "");
        }


    }
}
