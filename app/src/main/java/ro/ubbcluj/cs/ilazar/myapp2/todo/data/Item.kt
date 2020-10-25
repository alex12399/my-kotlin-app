package ro.ubbcluj.cs.ilazar.myapp2.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey @ColumnInfo(name = "_id") val _id: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "pages") var pages: Int,
    @ColumnInfo(name = "sold") var sold: Boolean,
    @ColumnInfo(name = "releaseDate") var releaseDate: String
) {
    override fun toString(): String = title
}