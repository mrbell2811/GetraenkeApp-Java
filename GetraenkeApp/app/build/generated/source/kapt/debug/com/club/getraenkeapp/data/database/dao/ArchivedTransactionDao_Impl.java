package com.club.getraenkeapp.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.club.getraenkeapp.data.database.entities.ArchivedTransaction;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ArchivedTransactionDao_Impl implements ArchivedTransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ArchivedTransaction> __insertionAdapterOfArchivedTransaction;

  private final EntityDeletionOrUpdateAdapter<ArchivedTransaction> __deletionAdapterOfArchivedTransaction;

  private final EntityDeletionOrUpdateAdapter<ArchivedTransaction> __updateAdapterOfArchivedTransaction;

  private final SharedSQLiteStatement __preparedStmtOfDeleteArchivedTransactionById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteArchivedTransactionsByPeriod;

  public ArchivedTransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArchivedTransaction = new EntityInsertionAdapter<ArchivedTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `archived_transactions` (`id`,`original_transaction_id`,`member_name`,`beverage_name`,`quantity`,`unit_price`,`total_price`,`transaction_timestamp`,`archived_timestamp`,`archive_period`,`archive_reason`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArchivedTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getOriginalTransactionId());
        if (entity.getMemberName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMemberName());
        }
        if (entity.getBeverageName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBeverageName());
        }
        statement.bindLong(5, entity.getQuantity());
        statement.bindDouble(6, entity.getUnitPrice());
        statement.bindDouble(7, entity.getTotalPrice());
        statement.bindLong(8, entity.getTransactionTimestamp());
        statement.bindLong(9, entity.getArchivedTimestamp());
        if (entity.getArchivePeriod() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getArchivePeriod());
        }
        if (entity.getArchiveReason() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getArchiveReason());
        }
      }
    };
    this.__deletionAdapterOfArchivedTransaction = new EntityDeletionOrUpdateAdapter<ArchivedTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `archived_transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArchivedTransaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfArchivedTransaction = new EntityDeletionOrUpdateAdapter<ArchivedTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `archived_transactions` SET `id` = ?,`original_transaction_id` = ?,`member_name` = ?,`beverage_name` = ?,`quantity` = ?,`unit_price` = ?,`total_price` = ?,`transaction_timestamp` = ?,`archived_timestamp` = ?,`archive_period` = ?,`archive_reason` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ArchivedTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getOriginalTransactionId());
        if (entity.getMemberName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMemberName());
        }
        if (entity.getBeverageName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBeverageName());
        }
        statement.bindLong(5, entity.getQuantity());
        statement.bindDouble(6, entity.getUnitPrice());
        statement.bindDouble(7, entity.getTotalPrice());
        statement.bindLong(8, entity.getTransactionTimestamp());
        statement.bindLong(9, entity.getArchivedTimestamp());
        if (entity.getArchivePeriod() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getArchivePeriod());
        }
        if (entity.getArchiveReason() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getArchiveReason());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteArchivedTransactionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM archived_transactions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteArchivedTransactionsByPeriod = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM archived_transactions WHERE archive_period = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertArchivedTransaction(final ArchivedTransaction transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfArchivedTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertArchivedTransactions(final List<ArchivedTransaction> transactions,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfArchivedTransaction.insertAndReturnIdsList(transactions);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteArchivedTransaction(final ArchivedTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfArchivedTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateArchivedTransaction(final ArchivedTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfArchivedTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteArchivedTransactionById(final long id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteArchivedTransactionById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteArchivedTransactionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteArchivedTransactionsByPeriod(final String period,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteArchivedTransactionsByPeriod.acquire();
        int _argIndex = 1;
        if (period == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, period);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteArchivedTransactionsByPeriod.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<ArchivedTransaction>> getAllArchivedTransactions() {
    final String _sql = "SELECT * FROM archived_transactions ORDER BY transaction_timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"archived_transactions"}, false, new Callable<List<ArchivedTransaction>>() {
      @Override
      @Nullable
      public List<ArchivedTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final List<ArchivedTransaction> _result = new ArrayList<ArchivedTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArchivedTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _item = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getArchivedTransactionById(final long id,
      final Continuation<? super ArchivedTransaction> $completion) {
    final String _sql = "SELECT * FROM archived_transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ArchivedTransaction>() {
      @Override
      @Nullable
      public ArchivedTransaction call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final ArchivedTransaction _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _result = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTransactionsByArchivePeriod(final String period,
      final Continuation<? super List<ArchivedTransaction>> $completion) {
    final String _sql = "SELECT * FROM archived_transactions WHERE archive_period = ? ORDER BY transaction_timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (period == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, period);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ArchivedTransaction>>() {
      @Override
      @NonNull
      public List<ArchivedTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final List<ArchivedTransaction> _result = new ArrayList<ArchivedTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArchivedTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _item = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getArchivedTransactionsByMember(final String memberName,
      final Continuation<? super List<ArchivedTransaction>> $completion) {
    final String _sql = "SELECT * FROM archived_transactions WHERE member_name = ? ORDER BY transaction_timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (memberName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, memberName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ArchivedTransaction>>() {
      @Override
      @NonNull
      public List<ArchivedTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final List<ArchivedTransaction> _result = new ArrayList<ArchivedTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArchivedTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _item = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getArchivedTransactionsByBeverage(final String beverageName,
      final Continuation<? super List<ArchivedTransaction>> $completion) {
    final String _sql = "SELECT * FROM archived_transactions WHERE beverage_name = ? ORDER BY transaction_timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (beverageName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, beverageName);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ArchivedTransaction>>() {
      @Override
      @NonNull
      public List<ArchivedTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final List<ArchivedTransaction> _result = new ArrayList<ArchivedTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArchivedTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _item = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getArchivedTransactionsInDateRange(final long startTimestamp,
      final long endTimestamp, final Continuation<? super List<ArchivedTransaction>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM archived_transactions \n"
            + "        WHERE transaction_timestamp BETWEEN ? AND ?\n"
            + "        ORDER BY transaction_timestamp ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTimestamp);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTimestamp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ArchivedTransaction>>() {
      @Override
      @NonNull
      public List<ArchivedTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfOriginalTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "original_transaction_id");
          final int _cursorIndexOfMemberName = CursorUtil.getColumnIndexOrThrow(_cursor, "member_name");
          final int _cursorIndexOfBeverageName = CursorUtil.getColumnIndexOrThrow(_cursor, "beverage_name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unit_price");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
          final int _cursorIndexOfTransactionTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_timestamp");
          final int _cursorIndexOfArchivedTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "archived_timestamp");
          final int _cursorIndexOfArchivePeriod = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_period");
          final int _cursorIndexOfArchiveReason = CursorUtil.getColumnIndexOrThrow(_cursor, "archive_reason");
          final List<ArchivedTransaction> _result = new ArrayList<ArchivedTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ArchivedTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpOriginalTransactionId;
            _tmpOriginalTransactionId = _cursor.getLong(_cursorIndexOfOriginalTransactionId);
            final String _tmpMemberName;
            if (_cursor.isNull(_cursorIndexOfMemberName)) {
              _tmpMemberName = null;
            } else {
              _tmpMemberName = _cursor.getString(_cursorIndexOfMemberName);
            }
            final String _tmpBeverageName;
            if (_cursor.isNull(_cursorIndexOfBeverageName)) {
              _tmpBeverageName = null;
            } else {
              _tmpBeverageName = _cursor.getString(_cursorIndexOfBeverageName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final long _tmpTransactionTimestamp;
            _tmpTransactionTimestamp = _cursor.getLong(_cursorIndexOfTransactionTimestamp);
            final long _tmpArchivedTimestamp;
            _tmpArchivedTimestamp = _cursor.getLong(_cursorIndexOfArchivedTimestamp);
            final String _tmpArchivePeriod;
            if (_cursor.isNull(_cursorIndexOfArchivePeriod)) {
              _tmpArchivePeriod = null;
            } else {
              _tmpArchivePeriod = _cursor.getString(_cursorIndexOfArchivePeriod);
            }
            final String _tmpArchiveReason;
            if (_cursor.isNull(_cursorIndexOfArchiveReason)) {
              _tmpArchiveReason = null;
            } else {
              _tmpArchiveReason = _cursor.getString(_cursorIndexOfArchiveReason);
            }
            _item = new ArchivedTransaction(_tmpId,_tmpOriginalTransactionId,_tmpMemberName,_tmpBeverageName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpTransactionTimestamp,_tmpArchivedTimestamp,_tmpArchivePeriod,_tmpArchiveReason);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getArchivePeriods(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT archive_period FROM archived_transactions ORDER BY archive_period DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalRevenueByPeriod(final String period,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(total_price) FROM archived_transactions WHERE archive_period = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (period == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, period);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTransactionCountByPeriod(final String period,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM archived_transactions WHERE archive_period = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (period == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, period);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastArchivePeriod(final Continuation<? super String> $completion) {
    final String _sql = "SELECT archive_period FROM archived_transactions ORDER BY archived_timestamp DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      @Nullable
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastArchiveTimestamp(final Continuation<? super Long> $completion) {
    final String _sql = "SELECT MAX(archived_timestamp) FROM archived_transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            final Long _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
