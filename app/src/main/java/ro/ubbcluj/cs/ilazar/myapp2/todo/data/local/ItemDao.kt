package ro.ubbcluj.cs.ilazar.myandroid.todo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import ro.ubbcluj.cs.ilazar.myapp2.todo.data.Item

@Dao
interface ItemDao {
    @Query("SELECT * from items ORDER BY title ASC")
    fun getAll(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE _id=:id ")
    fun getById(id: String): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: Item)

    @Query("DELETE FROM items")
    suspend fun deleteAll()
}