package com.club.getraenkeapp.data.database.dao;

/**
 * Data Access Object for Member operations.
 *
 * Provides CRUD operations for members with reactive LiveData queries
 * and suspend functions for background operations.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001d\u00a8\u0006\u001e"}, d2 = {"Lcom/club/getraenkeapp/data/database/dao/MemberDao;", "", "deleteMember", "", "member", "Lcom/club/getraenkeapp/data/database/entities/Member;", "(Lcom/club/getraenkeapp/data/database/entities/Member;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMemberById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMembers", "Landroidx/lifecycle/LiveData;", "", "getMaxGridPosition", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemberById", "getMemberByName", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMemberCount", "getTransactionCountForMember", "memberId", "insertMember", "updateMember", "updateMemberGridPosition", "position", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MemberDao {
    
    @androidx.room.Query(value = "SELECT * FROM members ORDER BY grid_position ASC, name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> getAllMembers();
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemberById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Member> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM members WHERE name = :name LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemberByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Member> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM members")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMemberCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT MAX(grid_position) FROM members")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMaxGridPosition(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM members WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMemberById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Check if member has any transactions before allowing deletion
     */
    @androidx.room.Query(value = "SELECT COUNT(*) FROM transactions WHERE member_id = :memberId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTransactionCountForMember(long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    /**
     * Update grid positions for layout configuration
     */
    @androidx.room.Query(value = "UPDATE members SET grid_position = :position WHERE id = :memberId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateMemberGridPosition(long memberId, int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}