package `in`.dotworld.todolistapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertText(todo:Entity)

    @Query("SELECT * FROM Entity")
    fun getText():List<Entity>
}