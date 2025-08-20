package com.club.getraenkeapp.data.repository;

/**
 * Repository pattern implementation for GeträkeApp3.0
 *
 * Single repository managing all data operations, injecting DAO instances via constructor,
 * exposing LiveData/Flow for UI layer consumption, and handling business logic like
 * transaction calculations.
 *
 * PATTERN: Repository handles business logic like transaction validation and calculations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0003\\]^B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ&\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"H\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u0012\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0*0)J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0*0)J\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0*0)J\u0012\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0*0)J\u0012\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0*0)J\u0014\u00100\u001a\b\u0012\u0004\u0012\u0002010*H\u0086@\u00a2\u0006\u0002\u00102J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020,0*2\u0006\u00104\u001a\u000201H\u0086@\u00a2\u0006\u0002\u00105J\u0018\u00106\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u00107\u001a\u0004\u0018\u00010\u001e2\u0006\u00108\u001a\u000201H\u0086@\u00a2\u0006\u0002\u00105J\u0012\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0*0)J\u0010\u0010;\u001a\u0004\u0018\u00010\u000eH\u0086@\u00a2\u0006\u0002\u00102J\u0018\u0010<\u001a\u0004\u0018\u00010\"2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010=\u001a\u0004\u0018\u00010\"2\u0006\u00108\u001a\u000201H\u0086@\u00a2\u0006\u0002\u00105J\u0016\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u000201H\u0086@\u00a2\u0006\u0002\u00105J,\u0010A\u001a\b\u0012\u0004\u0012\u00020B0*2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010EJ\u001e\u0010F\u001a\u00020G2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010HJ\u0018\u0010I\u001a\u0004\u0018\u00010&2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010J\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010K\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010L\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010HJ\u001a\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0*0)2\u0006\u0010\u0014\u001a\u00020\u000eJ$\u0010N\u001a\b\u0012\u0004\u0012\u00020B0*2\u0006\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010HJ\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020B0*H\u0086@\u00a2\u0006\u0002\u00102J\u0012\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0*0)J\u0016\u0010Q\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010R\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"H\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010S\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'J\u000e\u0010T\u001a\u00020UH\u0086@\u00a2\u0006\u0002\u00102J\u0016\u0010V\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010W\u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0086@\u00a2\u0006\u0002\u0010#J\u001e\u0010X\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010Y\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010ZJ\u0016\u0010[\u001a\u00020\f2\u0006\u0010%\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010\'R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006_"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository;", "", "memberDao", "Lcom/club/getraenkeapp/data/database/dao/MemberDao;", "beverageDao", "Lcom/club/getraenkeapp/data/database/dao/BeverageDao;", "transactionDao", "Lcom/club/getraenkeapp/data/database/dao/TransactionDao;", "archivedTransactionDao", "Lcom/club/getraenkeapp/data/database/dao/ArchivedTransactionDao;", "(Lcom/club/getraenkeapp/data/database/dao/MemberDao;Lcom/club/getraenkeapp/data/database/dao/BeverageDao;Lcom/club/getraenkeapp/data/database/dao/TransactionDao;Lcom/club/getraenkeapp/data/database/dao/ArchivedTransactionDao;)V", "activateBeverage", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canDeleteBeverage", "", "beverageId", "canDeleteMember", "memberId", "canSafelyDeleteBeverage", "canSafelyDeleteMember", "createTransaction", "quantity", "", "(JJILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateBeverage", "deleteBeverage", "beverage", "Lcom/club/getraenkeapp/data/database/entities/Beverage;", "(Lcom/club/getraenkeapp/data/database/entities/Beverage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMember", "member", "Lcom/club/getraenkeapp/data/database/entities/Member;", "(Lcom/club/getraenkeapp/data/database/entities/Member;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransaction", "transaction", "Lcom/club/getraenkeapp/data/database/entities/Transaction;", "(Lcom/club/getraenkeapp/data/database/entities/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveBeverages", "Landroidx/lifecycle/LiveData;", "", "getAllArchivedTransactions", "Lcom/club/getraenkeapp/data/database/entities/ArchivedTransaction;", "getAllBeverages", "getAllMembers", "getAllTransactions", "getArchivePeriods", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArchivedTransactionsByPeriod", "period", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBeverageById", "getBeverageByName", "name", "getConsumptionOverview", "Lcom/club/getraenkeapp/data/Consumption;", "getLastArchiveTimestamp", "getMemberById", "getMemberByName", "getMemberStatisticsSinceLastArchive", "Lcom/club/getraenkeapp/data/repository/AppRepository$MemberStatistics;", "memberName", "getMemberTransactionsInDateRange", "Lcom/club/getraenkeapp/data/database/entities/TransactionWithDetails;", "startTimestamp", "endTimestamp", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalRevenueInDateRange", "", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionById", "getTransactionCountForBeverage", "getTransactionCountForMember", "getTransactionCountInDateRange", "getTransactionsByMember", "getTransactionsInDateRange", "getTransactionsSinceLastArchive", "getTransactionsWithDetails", "insertBeverage", "insertMember", "insertTransaction", "performMonthlyArchive", "Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult;", "updateBeverage", "updateMember", "updateMemberGridPosition", "position", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTransaction", "ArchiveResult", "BeverageConsumption", "MemberStatistics", "app_debug"})
public final class AppRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.database.dao.MemberDao memberDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.database.dao.BeverageDao beverageDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.database.dao.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao archivedTransactionDao = null;
    
    public AppRepository(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.dao.MemberDao memberDao, @org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.dao.BeverageDao beverageDao, @org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.dao.TransactionDao transactionDao, @org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao archivedTransactionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Member>> getAllMembers() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMemberById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Member> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMemberByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Member> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteMember(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Member member, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateMemberGridPosition(long memberId, int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getActiveBeverages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Beverage>> getAllBeverages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBeverageById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Beverage> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBeverageByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Beverage> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBeverage(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Beverage beverage, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object activateBeverage(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deactivateBeverage(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Transaction>> getAllTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> getTransactionsWithDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.Consumption>> getConsumptionOverview() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.database.entities.Transaction> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.Transaction>> getTransactionsByMember(long memberId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.club.getraenkeapp.data.database.entities.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionsInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMemberTransactionsInDateRange(long memberId, long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTotalRevenueInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionCountInDateRange(long startTimestamp, long endTimestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Create a transaction with automatic total calculation
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createTransaction(long memberId, long beverageId, int quantity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Validate if member can be deleted (no transactions)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object canDeleteMember(long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Validate if beverage can be deleted (no transactions)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object canDeleteBeverage(long beverageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Get transaction count for member (for deletion validation)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionCountForMember(long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Get transaction count for beverage (for deletion validation)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionCountForBeverage(long beverageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Get all archived transactions
     */
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> getAllArchivedTransactions() {
        return null;
    }
    
    /**
     * Archive current month transactions and clear them from active table
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object performMonthlyArchive(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult> $completion) {
        return null;
    }
    
    /**
     * Get archived transactions by period
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getArchivedTransactionsByPeriod(@org.jetbrains.annotations.NotNull()
    java.lang.String period, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.ArchivedTransaction>> $completion) {
        return null;
    }
    
    /**
     * Get all archive periods
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getArchivePeriods(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    /**
     * Get last archive timestamp (when last monthly closing occurred)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLastArchiveTimestamp(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Get transactions since last archive (for current period statistics)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionsSinceLastArchive(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.club.getraenkeapp.data.database.entities.TransactionWithDetails>> $completion) {
        return null;
    }
    
    /**
     * Get member statistics since last archive
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMemberStatisticsSinceLastArchive(@org.jetbrains.annotations.NotNull()
    java.lang.String memberName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.club.getraenkeapp.data.repository.AppRepository.MemberStatistics> $completion) {
        return null;
    }
    
    /**
     * Check if beverage can be safely deleted (no active transactions, archived data preserved)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object canSafelyDeleteBeverage(long beverageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Check if member can be safely deleted (no active transactions, archived data preserved)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object canSafelyDeleteMember(long memberId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult;", "", "()V", "Error", "Success", "Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult$Error;", "Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult$Success;", "app_debug"})
    public static abstract class ArchiveResult {
        
        private ArchiveResult() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult$Error;", "Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0005H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult$Success;", "Lcom/club/getraenkeapp/data/repository/AppRepository$ArchiveResult;", "archivedCount", "", "archivePeriod", "", "message", "(ILjava/lang/String;Ljava/lang/String;)V", "getArchivePeriod", "()Ljava/lang/String;", "getArchivedCount", "()I", "getMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "app_debug"})
        public static final class Success extends com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult {
            private final int archivedCount = 0;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String archivePeriod = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Success(int archivedCount, @org.jetbrains.annotations.NotNull()
            java.lang.String archivePeriod, @org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            public final int getArchivedCount() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getArchivePeriod() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            public final int component1() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.club.getraenkeapp.data.repository.AppRepository.ArchiveResult.Success copy(int archivedCount, @org.jetbrains.annotations.NotNull()
            java.lang.String archivePeriod, @org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository$BeverageConsumption;", "", "beverageName", "", "quantity", "", "totalPrice", "", "(Ljava/lang/String;ID)V", "getBeverageName", "()Ljava/lang/String;", "getQuantity", "()I", "getTotalPrice", "()D", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class BeverageConsumption {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String beverageName = null;
        private final int quantity = 0;
        private final double totalPrice = 0.0;
        
        public BeverageConsumption(@org.jetbrains.annotations.NotNull()
        java.lang.String beverageName, int quantity, double totalPrice) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBeverageName() {
            return null;
        }
        
        public final int getQuantity() {
            return 0;
        }
        
        public final double getTotalPrice() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption copy(@org.jetbrains.annotations.NotNull()
        java.lang.String beverageName, int quantity, double totalPrice) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\fH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0005H\u00d6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016\u00a8\u0006&"}, d2 = {"Lcom/club/getraenkeapp/data/repository/AppRepository$MemberStatistics;", "", "memberName", "", "totalQuantity", "", "totalSpent", "", "beverageBreakdown", "", "Lcom/club/getraenkeapp/data/repository/AppRepository$BeverageConsumption;", "periodStart", "", "transactionCount", "(Ljava/lang/String;IDLjava/util/List;JI)V", "getBeverageBreakdown", "()Ljava/util/List;", "getMemberName", "()Ljava/lang/String;", "getPeriodStart", "()J", "getTotalQuantity", "()I", "getTotalSpent", "()D", "getTransactionCount", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class MemberStatistics {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String memberName = null;
        private final int totalQuantity = 0;
        private final double totalSpent = 0.0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> beverageBreakdown = null;
        private final long periodStart = 0L;
        private final int transactionCount = 0;
        
        public MemberStatistics(@org.jetbrains.annotations.NotNull()
        java.lang.String memberName, int totalQuantity, double totalSpent, @org.jetbrains.annotations.NotNull()
        java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> beverageBreakdown, long periodStart, int transactionCount) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMemberName() {
            return null;
        }
        
        public final int getTotalQuantity() {
            return 0;
        }
        
        public final double getTotalSpent() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> getBeverageBreakdown() {
            return null;
        }
        
        public final long getPeriodStart() {
            return 0L;
        }
        
        public final int getTransactionCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> component4() {
            return null;
        }
        
        public final long component5() {
            return 0L;
        }
        
        public final int component6() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.data.repository.AppRepository.MemberStatistics copy(@org.jetbrains.annotations.NotNull()
        java.lang.String memberName, int totalQuantity, double totalSpent, @org.jetbrains.annotations.NotNull()
        java.util.List<com.club.getraenkeapp.data.repository.AppRepository.BeverageConsumption> beverageBreakdown, long periodStart, int transactionCount) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}