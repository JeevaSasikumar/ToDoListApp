package `in`.dotworld.todolistapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entity")
data class Entity (
    @PrimaryKey
    var todo:String
)

