package com.example.rishabh.simplenotesapp.database;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Rishabh on 1/26/17.
 */

@Database(version = NotesDatabase.VERSION)

public class NotesDatabase {

    public static final int VERSION = 2;

    @Table(NotesColumns.class)
    public static final String NOTES = "notes";
}
