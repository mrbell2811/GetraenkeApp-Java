package com.club.getraenkeapp.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao;
import com.club.getraenkeapp.data.database.dao.ArchivedTransactionDao_Impl;
import com.club.getraenkeapp.data.database.dao.BeverageDao;
import com.club.getraenkeapp.data.database.dao.BeverageDao_Impl;
import com.club.getraenkeapp.data.database.dao.MemberDao;
import com.club.getraenkeapp.data.database.dao.MemberDao_Impl;
import com.club.getraenkeapp.data.database.dao.TransactionDao;
import com.club.getraenkeapp.data.database.dao.TransactionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile MemberDao _memberDao;

  private volatile BeverageDao _beverageDao;

  private volatile TransactionDao _transactionDao;

  private volatile ArchivedTransactionDao _archivedTransactionDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `members` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `grid_position` INTEGER, `created_at` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `beverages` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `price` REAL NOT NULL, `category` TEXT, `active` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `member_id` INTEGER NOT NULL, `beverage_id` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, `unit_price` REAL NOT NULL, `total_price` REAL NOT NULL, `timestamp` INTEGER NOT NULL, FOREIGN KEY(`member_id`) REFERENCES `members`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT , FOREIGN KEY(`beverage_id`) REFERENCES `beverages`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `archived_transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `original_transaction_id` INTEGER NOT NULL, `member_name` TEXT NOT NULL, `beverage_name` TEXT NOT NULL, `quantity` INTEGER NOT NULL, `unit_price` REAL NOT NULL, `total_price` REAL NOT NULL, `transaction_timestamp` INTEGER NOT NULL, `archived_timestamp` INTEGER NOT NULL, `archive_period` TEXT NOT NULL, `archive_reason` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a778bd467148186f37bcb8f49429e7bc')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `members`");
        db.execSQL("DROP TABLE IF EXISTS `beverages`");
        db.execSQL("DROP TABLE IF EXISTS `transactions`");
        db.execSQL("DROP TABLE IF EXISTS `archived_transactions`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsMembers = new HashMap<String, TableInfo.Column>(4);
        _columnsMembers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMembers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMembers.put("grid_position", new TableInfo.Column("grid_position", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMembers.put("created_at", new TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMembers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMembers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMembers = new TableInfo("members", _columnsMembers, _foreignKeysMembers, _indicesMembers);
        final TableInfo _existingMembers = TableInfo.read(db, "members");
        if (!_infoMembers.equals(_existingMembers)) {
          return new RoomOpenHelper.ValidationResult(false, "members(com.club.getraenkeapp.data.database.entities.Member).\n"
                  + " Expected:\n" + _infoMembers + "\n"
                  + " Found:\n" + _existingMembers);
        }
        final HashMap<String, TableInfo.Column> _columnsBeverages = new HashMap<String, TableInfo.Column>(5);
        _columnsBeverages.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBeverages.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBeverages.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBeverages.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBeverages.put("active", new TableInfo.Column("active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBeverages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBeverages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBeverages = new TableInfo("beverages", _columnsBeverages, _foreignKeysBeverages, _indicesBeverages);
        final TableInfo _existingBeverages = TableInfo.read(db, "beverages");
        if (!_infoBeverages.equals(_existingBeverages)) {
          return new RoomOpenHelper.ValidationResult(false, "beverages(com.club.getraenkeapp.data.database.entities.Beverage).\n"
                  + " Expected:\n" + _infoBeverages + "\n"
                  + " Found:\n" + _existingBeverages);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactions = new HashMap<String, TableInfo.Column>(7);
        _columnsTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("member_id", new TableInfo.Column("member_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("beverage_id", new TableInfo.Column("beverage_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("unit_price", new TableInfo.Column("unit_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("total_price", new TableInfo.Column("total_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactions = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysTransactions.add(new TableInfo.ForeignKey("members", "RESTRICT", "NO ACTION", Arrays.asList("member_id"), Arrays.asList("id")));
        _foreignKeysTransactions.add(new TableInfo.ForeignKey("beverages", "RESTRICT", "NO ACTION", Arrays.asList("beverage_id"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactions = new TableInfo("transactions", _columnsTransactions, _foreignKeysTransactions, _indicesTransactions);
        final TableInfo _existingTransactions = TableInfo.read(db, "transactions");
        if (!_infoTransactions.equals(_existingTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "transactions(com.club.getraenkeapp.data.database.entities.Transaction).\n"
                  + " Expected:\n" + _infoTransactions + "\n"
                  + " Found:\n" + _existingTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsArchivedTransactions = new HashMap<String, TableInfo.Column>(11);
        _columnsArchivedTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("original_transaction_id", new TableInfo.Column("original_transaction_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("member_name", new TableInfo.Column("member_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("beverage_name", new TableInfo.Column("beverage_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("unit_price", new TableInfo.Column("unit_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("total_price", new TableInfo.Column("total_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("transaction_timestamp", new TableInfo.Column("transaction_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("archived_timestamp", new TableInfo.Column("archived_timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("archive_period", new TableInfo.Column("archive_period", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArchivedTransactions.put("archive_reason", new TableInfo.Column("archive_reason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArchivedTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArchivedTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArchivedTransactions = new TableInfo("archived_transactions", _columnsArchivedTransactions, _foreignKeysArchivedTransactions, _indicesArchivedTransactions);
        final TableInfo _existingArchivedTransactions = TableInfo.read(db, "archived_transactions");
        if (!_infoArchivedTransactions.equals(_existingArchivedTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "archived_transactions(com.club.getraenkeapp.data.database.entities.ArchivedTransaction).\n"
                  + " Expected:\n" + _infoArchivedTransactions + "\n"
                  + " Found:\n" + _existingArchivedTransactions);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a778bd467148186f37bcb8f49429e7bc", "1d55a5ad668d59aded4d677a5c094640");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "members","beverages","transactions","archived_transactions");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `transactions`");
      _db.execSQL("DELETE FROM `members`");
      _db.execSQL("DELETE FROM `beverages`");
      _db.execSQL("DELETE FROM `archived_transactions`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MemberDao.class, MemberDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BeverageDao.class, BeverageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ArchivedTransactionDao.class, ArchivedTransactionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MemberDao memberDao() {
    if (_memberDao != null) {
      return _memberDao;
    } else {
      synchronized(this) {
        if(_memberDao == null) {
          _memberDao = new MemberDao_Impl(this);
        }
        return _memberDao;
      }
    }
  }

  @Override
  public BeverageDao beverageDao() {
    if (_beverageDao != null) {
      return _beverageDao;
    } else {
      synchronized(this) {
        if(_beverageDao == null) {
          _beverageDao = new BeverageDao_Impl(this);
        }
        return _beverageDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public ArchivedTransactionDao archivedTransactionDao() {
    if (_archivedTransactionDao != null) {
      return _archivedTransactionDao;
    } else {
      synchronized(this) {
        if(_archivedTransactionDao == null) {
          _archivedTransactionDao = new ArchivedTransactionDao_Impl(this);
        }
        return _archivedTransactionDao;
      }
    }
  }
}
