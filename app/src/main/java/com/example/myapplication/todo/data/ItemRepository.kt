package com.example.myapplication.todo.data

import com.example.myapplication.core.Result
import com.example.myapplication.todo.data.remote.ItemApi

object ItemRepository {
    private var cachedItems: MutableList<Item>? = null;

    suspend fun loadAll(): Result<List<Item>> {
        if (cachedItems != null) {
            return Result.Success(cachedItems as List<Item>);
        }
        try {
            val items = ItemApi.service.find()
            cachedItems = mutableListOf()
            cachedItems?.addAll(items)
            return Result.Success(cachedItems as List<Item>)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun load(itemId: String): Result<Item> {
        val item = cachedItems?.find { it._id == itemId }
        if (item != null) {
            return Result.Success(item)
        }
        try {
            return Result.Success(ItemApi.service.read(itemId))
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun save(item: Item): Result<Item> {
        try {
            val createdItem = ItemApi.service.create(item)
            cachedItems?.add(createdItem)
            return Result.Success(createdItem)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    suspend fun update(item: Item): Result<Item> {
        try {
            val updatedItem = ItemApi.service.update(item._id, item)
            val index = cachedItems?.indexOfFirst { it._id == item._id }
            if (index != null) {
                cachedItems?.set(index, updatedItem)
            }
            return Result.Success(updatedItem)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
    suspend fun delete(itemId: String): Result<Boolean>
    {
        try {

            val index = cachedItems?.indexOfFirst { it._id == itemId }
            if (index!=null)
            {
                cachedItems?.removeAt(index)
            }
            return Result.Success(true)
        }
        catch (e: Exception) {
            return Result.Error(e)
        }
    }
}