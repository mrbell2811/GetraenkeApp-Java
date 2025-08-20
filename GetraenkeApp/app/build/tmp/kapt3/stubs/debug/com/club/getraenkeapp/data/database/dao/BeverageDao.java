package com.club.getraenkeapp.data.database.dao;

/**
 * Data Access Object for Beverage operations.
 *
 * Provides CRUD operations for beverages with filtering for active items
 * and reactive LiveData queries for UI updates.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u000eH\'J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u000eH\'J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u000e2\u0006\u0010\u0019\u001a\u00020\u0012H\'J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u001d\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lcom/club/getraenkeapp/data/database/dao/BeverageDao;", "", "activateBeverage", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateBeverage", "deleteBeverage", "beverage", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "(Lcom/club/getraenkeapp/data/database/entities/Beverage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBeverageById", "getActiveBeverages", "Landroidx/lifecycle/LiveData;", "", "getAllBeverages", "getAllCategories", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBeverageById", "getBeverageByName", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBeveragesByCategory", "category", "getTransactionCountForBeverage", "", "beverageId", "insertBeverage", "updateBeverage", "app_debug"})
@androidx.room.Dao()
public abstract interface BeverageDao {
    
    @androidx.room.Query(value = "SELECT * FROM beverages WHERE active = 1 ORDER BY category ASC, name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getActiveBeverages();
    
    @androidx.room.Query(value = "SELECT * FROM beverages ORDER BY category ASC, name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getAllBeverages();
    
    @androidx.room.Query(value = "SELECT * FROM beverages WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBeverageById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Beverage> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM beverages WHERE name = :name LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBeverageByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Beverage> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM beverages WHERE category = :category AND active = 1 ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getBeveragesByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT DISTINCT category FROM beverages WHERE category IS NOT NULL ORDER BY category ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM beverages WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBeverageById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Deactivate beverage instead of deleting (safer for data integrity)
     */
    @androidx.room.Query(value = "UPDATE beverages SET active = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deactivateBeverage(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Reactivate beverage
     */
    @androidx.room.Query(value = "UPDATE beverages SET active = 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object activateBeverage(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Check if beverage has any transactions before allowing deletion
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM transactions WHERE beverage_id = :beverageId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionCountForBeverage(long beverageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}