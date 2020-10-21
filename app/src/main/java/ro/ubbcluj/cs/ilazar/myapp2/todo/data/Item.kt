package ro.ubbcluj.cs.ilazar.myapp2.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "text") var text: String
) {
    override fun toString(): String = text
}