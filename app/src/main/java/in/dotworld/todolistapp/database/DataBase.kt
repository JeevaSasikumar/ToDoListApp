package `in`.dotworld.todolistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = [Entity::class],
    version=1)
abstract  class DataBase :RoomDatabase() {
     abstract fun doToDo() : Dao

    companion object {
        @Volatile
        private var instance: RoomDatabase? = null

        fun getInstance(context: Context): DataBase {
            return instance as DataBase? ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, DataBase::class.java, "listdb")
                    .allowMainThreadQueries()
                    .build()
            } as DataBase
        }
    }
}