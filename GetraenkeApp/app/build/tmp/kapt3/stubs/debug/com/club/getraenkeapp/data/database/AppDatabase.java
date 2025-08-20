package com.club.getraenkeapp.data.database;

/**
 * Room Database for GeträkeApp3.0
 *
 * Central database class that provides access to all DAOs and manages
 * database creation, versioning, and initial data seeding.
 *
 * CRITICAL: SQLite database name should be consistent
 * CRITICAL: Room requires suspend functions for DAO operations
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/club/getraenkeapp/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "archivedTransactionDao", "Lcom/club/getraenkeapp/data/database/dao/ArchivedTransactionDao;", "beverageDao", "Lcom/club/getraenkeapp/data/database/dao/BeverageDao;", "memberDao", "Lcom/club/getraenkeapp/data/database/dao/MemberDao;", "transactionDao", "Lcom/club/getraenkeapp/data/database/dao/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.club.getraenkeapp.data.database.entities.Member.class, com.club.getraenkeapp.data.database.entities.Beverage.class, com.club.getraenkeapp.data.database.entities.Transaction.class, com.club.getraenkeapp.data.database.entities.ArchivedTransaction.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.club.getraenkeapp.data.database.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DATABASE_NAME = "getraenke_database";
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.club.getraenkeapp.data.database.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.club.getraenkeapp.data.database.dao.MemberDao memberDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.club.getraenkeapp.data.database.dao.BeverageDao beverageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.club.getraenkeapp.data.database.dao.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao archivedTransactionDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/club/getraenkeapp/data/database/AppDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "INSTANCE", "Lcom/club/getraenkeapp/data/database/AppDatabase;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "getDatabase", "context", "Landroid/content/Context;", "populateDatabase", "", "database", "(Lcom/club/getraenkeapp/data/database/AppDatabase;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DatabaseCallback", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.club.getraenkeapp.data.database.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        /**
         * Populate database with initial test data for development
         */
        private final java.lang.Object populateDatabase(com.club.getraenkeapp.data.database.AppDatabase database, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        /**
         * Database callback to seed initial data
         */
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/club/getraenkeapp/data/database/AppDatabase$Companion$DatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "app_debug"})
        static final class DatabaseCallback extends androidx.room.RoomDatabase.Callback {
            
            public DatabaseCallback() {
                super();
            }
            
            @java.lang.Override()
            public void onCreate(@org.jetbrains.annotations.NotNull()
            androidx.sqlite.db.SupportSQLiteDatabase db) {
            }
        }
    }
}