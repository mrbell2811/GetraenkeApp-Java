package com.club.getraenkeapp.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.club.getraenkeapp.data.entities.Beverage;
import com.club.getraenkeapp.data.entities.Member;
import com.club.getraenkeapp.data.entities.Transaction;
import com.club.getraenkeapp.data.entities.TransactionWithDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLiteOpenHelper implementation for GeträkeApp database management.
 * 
 * Handles database creation, upgrades, and provides CRUD operations
 * for all entities (Member, Beverage, Transaction).
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String TAG = "DatabaseHelper";
    
    // Database Info
    private static final String DATABASE_NAME = "getraenke_database.db";
    private static final int DATABASE_VERSION = 1;
    
    // Singleton instance
    private static DatabaseHelper instance;
    
    // Private constructor to enforce singleton
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    /**
     * Get singleton instance of DatabaseHelper
     */
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Creating database tables");
        
        try {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
            
            // Create tables
            db.execSQL(Member.CREATE_TABLE);
            Log.d(TAG, "Created members table");
            
            db.execSQL(Beverage.CREATE_TABLE);
            Log.d(TAG, "Created beverages table");
            
            db.execSQL(Transaction.CREATE_TABLE);
            Log.d(TAG, "Created transactions table");
            
            // Insert sample data for development
            insertSampleData(db);
            
        } catch (SQLException e) {
            Log.e(TAG, "Error creating database tables", e);
            throw e;
        }
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        
        if (oldVersion < newVersion) {
            // For now, simple upgrade strategy: drop and recreate
            // In production, implement proper migration logic
            db.execSQL("DROP TABLE IF EXISTS " + Transaction.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Beverage.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + Member.TABLE_NAME);
            onCreate(db);
        }
    }
    
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // Enable foreign key constraints
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    
    /**
     * Insert sample data for development and testing
     */
    private void insertSampleData(SQLiteDatabase db) {
        Log.d(TAG, "Inserting sample data");
        
        // Sample members
        String[] memberNames = {
            "Alex Müller", "Berta Schmidt", "Christian Weber", "Diana Fischer",
            "Emil Wagner", "Franziska Becker", "Georg Schulz", "Hannah Hoffmann",
            "Igor Klein", "Julia Koch", "Klaus Richter", "Lisa Neumann",
            "Martin Braun", "Nina Zimmermann", "Oliver Krüger", "Petra Schmitt",
            "Quentin Wolf", "Rita Lange", "Stefan Baumann", "Tina Krause"
        };
        
        for (int i = 0; i < memberNames.length; i++) {
            ContentValues memberValues = new ContentValues();
            memberValues.put(Member.COLUMN_NAME, memberNames[i]);
            memberValues.put(Member.COLUMN_GRID_POSITION, i + 1);
            memberValues.put(Member.COLUMN_CREATED_AT, System.currentTimeMillis());
            
            long memberId = db.insert(Member.TABLE_NAME, null, memberValues);
            if (memberId == -1) {
                Log.w(TAG, "Failed to insert member: " + memberNames[i]);
            }
        }
        
        // Sample beverages
        Object[][] beverageData = {
            {"Bier 0,5L", 2.50, "Alkoholisch"},
            {"Weizen 0,5L", 2.80, "Alkoholisch"},
            {"Cola 0,33L", 1.50, "Kalt"},
            {"Sprite 0,33L", 1.50, "Kalt"},
            {"Wasser 0,5L", 1.00, "Kalt"},
            {"Kaffee", 1.20, "Warm"},
            {"Tee", 1.00, "Warm"},
            {"Orangensaft 0,2L", 1.80, "Kalt"},
            {"Apfelschorle 0,33L", 1.60, "Kalt"},
            {"Radler 0,5L", 2.30, "Alkoholisch"}
        };
        
        for (Object[] bevData : beverageData) {
            ContentValues beverageValues = new ContentValues();
            beverageValues.put(Beverage.COLUMN_NAME, (String) bevData[0]);
            beverageValues.put(Beverage.COLUMN_PRICE, (Double) bevData[1]);
            beverageValues.put(Beverage.COLUMN_CATEGORY, (String) bevData[2]);
            beverageValues.put(Beverage.COLUMN_ACTIVE, 1);
            
            long beverageId = db.insert(Beverage.TABLE_NAME, null, beverageValues);
            if (beverageId == -1) {
                Log.w(TAG, "Failed to insert beverage: " + bevData[0]);
            }
        }
        
        Log.d(TAG, "Sample data inserted successfully");
    }
    
    // ==== MEMBER CRUD OPERATIONS ====
    
    /**
     * Insert a new member
     */
    public long insertMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Member.COLUMN_NAME, member.getName());
        values.put(Member.COLUMN_GRID_POSITION, member.getGridPosition());
        values.put(Member.COLUMN_CREATED_AT, member.getCreatedAt());
        
        long id = db.insert(Member.TABLE_NAME, null, values);
        
        if (id != -1) {
            member.setId(id);
            Log.d(TAG, "Inserted member: " + member.getName() + " with ID: " + id);
        } else {
            Log.e(TAG, "Failed to insert member: " + member.getName());
        }
        
        return id;
    }
    
    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String query = "SELECT * FROM " + Member.TABLE_NAME + " ORDER BY " + Member.COLUMN_GRID_POSITION + ", " + Member.COLUMN_NAME;
        
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Member member = cursorToMember(cursor);
                    members.add(member);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all members", e);
        }
        
        return members;
    }
    
    /**
     * Get member by ID
     */
    public Member getMemberById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selection = Member.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        
        try (Cursor cursor = db.query(Member.TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            if (cursor.moveToFirst()) {
                return cursorToMember(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting member by ID: " + id, e);
        }
        
        return null;
    }
    
    /**
     * Update member
     */
    public int updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Member.COLUMN_NAME, member.getName());
        values.put(Member.COLUMN_GRID_POSITION, member.getGridPosition());
        
        String whereClause = Member.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(member.getId())};
        
        int rowsAffected = db.update(Member.TABLE_NAME, values, whereClause, whereArgs);
        
        if (rowsAffected > 0) {
            Log.d(TAG, "Updated member: " + member.getName());
        } else {
            Log.w(TAG, "No rows affected when updating member: " + member.getId());
        }
        
        return rowsAffected;
    }
    
    /**
     * Delete member (only if no transactions exist)
     */
    public boolean deleteMember(long memberId) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        // Check if member has transactions
        if (hasTransactions(memberId, -1)) {
            Log.w(TAG, "Cannot delete member " + memberId + " - has existing transactions");
            return false;
        }
        
        String whereClause = Member.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(memberId)};
        
        int rowsDeleted = db.delete(Member.TABLE_NAME, whereClause, whereArgs);
        
        if (rowsDeleted > 0) {
            Log.d(TAG, "Deleted member with ID: " + memberId);
            return true;
        } else {
            Log.w(TAG, "No member found with ID: " + memberId);
            return false;
        }
    }
    
    // ==== BEVERAGE CRUD OPERATIONS ====
    
    /**
     * Insert a new beverage
     */
    public long insertBeverage(Beverage beverage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Beverage.COLUMN_NAME, beverage.getName());
        values.put(Beverage.COLUMN_PRICE, beverage.getPrice());
        values.put(Beverage.COLUMN_CATEGORY, beverage.getCategory());
        values.put(Beverage.COLUMN_ACTIVE, beverage.isActive() ? 1 : 0);
        
        long id = db.insert(Beverage.TABLE_NAME, null, values);
        
        if (id != -1) {
            beverage.setId(id);
            Log.d(TAG, "Inserted beverage: " + beverage.getName() + " with ID: " + id);
        } else {
            Log.e(TAG, "Failed to insert beverage: " + beverage.getName());
        }
        
        return id;
    }
    
    /**
     * Get all active beverages
     */
    public List<Beverage> getActiveBeverages() {
        List<Beverage> beverages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selection = Beverage.COLUMN_ACTIVE + " = ?";
        String[] selectionArgs = {"1"};
        String orderBy = Beverage.COLUMN_CATEGORY + ", " + Beverage.COLUMN_NAME;
        
        try (Cursor cursor = db.query(Beverage.TABLE_NAME, null, selection, selectionArgs, null, null, orderBy)) {
            if (cursor.moveToFirst()) {
                do {
                    Beverage beverage = cursorToBeverage(cursor);
                    beverages.add(beverage);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting active beverages", e);
        }
        
        return beverages;
    }
    
    /**
     * Get all beverages (including inactive)
     */
    public List<Beverage> getAllBeverages() {
        List<Beverage> beverages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String orderBy = Beverage.COLUMN_ACTIVE + " DESC, " + Beverage.COLUMN_CATEGORY + ", " + Beverage.COLUMN_NAME;
        
        try (Cursor cursor = db.query(Beverage.TABLE_NAME, null, null, null, null, null, orderBy)) {
            if (cursor.moveToFirst()) {
                do {
                    Beverage beverage = cursorToBeverage(cursor);
                    beverages.add(beverage);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting all beverages", e);
        }
        
        return beverages;
    }
    
    /**
     * Get beverage by ID
     */
    public Beverage getBeverageById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String selection = Beverage.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        
        try (Cursor cursor = db.query(Beverage.TABLE_NAME, null, selection, selectionArgs, null, null, null)) {
            if (cursor.moveToFirst()) {
                return cursorToBeverage(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting beverage by ID: " + id, e);
        }
        
        return null;
    }
    
    /**
     * Update beverage
     */
    public int updateBeverage(Beverage beverage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Beverage.COLUMN_NAME, beverage.getName());
        values.put(Beverage.COLUMN_PRICE, beverage.getPrice());
        values.put(Beverage.COLUMN_CATEGORY, beverage.getCategory());
        values.put(Beverage.COLUMN_ACTIVE, beverage.isActive() ? 1 : 0);
        
        String whereClause = Beverage.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(beverage.getId())};
        
        int rowsAffected = db.update(Beverage.TABLE_NAME, values, whereClause, whereArgs);
        
        if (rowsAffected > 0) {
            Log.d(TAG, "Updated beverage: " + beverage.getName());
        } else {
            Log.w(TAG, "No rows affected when updating beverage: " + beverage.getId());
        }
        
        return rowsAffected;
    }
    
    /**
     * Delete beverage (only if no transactions exist)
     */
    public boolean deleteBeverage(long beverageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        
        // Check if beverage has transactions
        if (hasTransactions(-1, beverageId)) {
            Log.w(TAG, "Cannot delete beverage " + beverageId + " - has existing transactions");
            return false;
        }
        
        String whereClause = Beverage.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(beverageId)};
        
        int rowsDeleted = db.delete(Beverage.TABLE_NAME, whereClause, whereArgs);
        
        if (rowsDeleted > 0) {
            Log.d(TAG, "Deleted beverage with ID: " + beverageId);
            return true;
        } else {
            Log.w(TAG, "No beverage found with ID: " + beverageId);
            return false;
        }
    }
    
    // ==== TRANSACTION CRUD OPERATIONS ====
    
    /**
     * Insert a new transaction
     */
    public long insertTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(Transaction.COLUMN_MEMBER_ID, transaction.getMemberId());
        values.put(Transaction.COLUMN_BEVERAGE_ID, transaction.getBeverageId());
        values.put(Transaction.COLUMN_QUANTITY, transaction.getQuantity());
        values.put(Transaction.COLUMN_UNIT_PRICE, transaction.getUnitPrice());
        values.put(Transaction.COLUMN_TOTAL_PRICE, transaction.getTotalPrice());
        values.put(Transaction.COLUMN_TIMESTAMP, transaction.getTimestamp());
        
        long id = db.insert(Transaction.TABLE_NAME, null, values);
        
        if (id != -1) {
            transaction.setId(id);
            Log.d(TAG, "Inserted transaction with ID: " + id);
        } else {
            Log.e(TAG, "Failed to insert transaction");
        }
        
        return id;
    }
    
    /**
     * Get transactions with member and beverage details for date range
     */
    public List<TransactionWithDetails> getTransactionsWithDetails(long startTimestamp, long endTimestamp) {
        List<TransactionWithDetails> transactions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        String query = "SELECT " +
            "t." + Transaction.COLUMN_ID + ", " +
            "t." + Transaction.COLUMN_MEMBER_ID + ", " +
            "m." + Member.COLUMN_NAME + ", " +
            "t." + Transaction.COLUMN_BEVERAGE_ID + ", " +
            "b." + Beverage.COLUMN_NAME + ", " +
            "t." + Transaction.COLUMN_QUANTITY + ", " +
            "t." + Transaction.COLUMN_UNIT_PRICE + ", " +
            "t." + Transaction.COLUMN_TOTAL_PRICE + ", " +
            "t." + Transaction.COLUMN_TIMESTAMP +
            " FROM " + Transaction.TABLE_NAME + " t " +
            " JOIN " + Member.TABLE_NAME + " m ON t." + Transaction.COLUMN_MEMBER_ID + " = m." + Member.COLUMN_ID +
            " JOIN " + Beverage.TABLE_NAME + " b ON t." + Transaction.COLUMN_BEVERAGE_ID + " = b." + Beverage.COLUMN_ID +
            " WHERE t." + Transaction.COLUMN_TIMESTAMP + " >= ? AND t." + Transaction.COLUMN_TIMESTAMP + " <= ?" +
            " ORDER BY t." + Transaction.COLUMN_TIMESTAMP + " DESC";
        
        String[] selectionArgs = {String.valueOf(startTimestamp), String.valueOf(endTimestamp)};
        
        try (Cursor cursor = db.rawQuery(query, selectionArgs)) {
            if (cursor.moveToFirst()) {
                do {
                    TransactionWithDetails transaction = cursorToTransactionWithDetails(cursor);
                    transactions.add(transaction);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting transactions with details", e);
        }
        
        return transactions;
    }
    
    /**
     * Get all transactions with details (for admin overview)
     */
    public List<TransactionWithDetails> getAllTransactionsWithDetails() {
        return getTransactionsWithDetails(0, System.currentTimeMillis());
    }
    
    /**
     * Check if member or beverage has existing transactions
     */
    private boolean hasTransactions(long memberId, long beverageId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query;
        String[] args;
        
        if (memberId > 0) {
            query = "SELECT COUNT(*) FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_MEMBER_ID + " = ?";
            args = new String[]{String.valueOf(memberId)};
        } else if (beverageId > 0) {
            query = "SELECT COUNT(*) FROM " + Transaction.TABLE_NAME + " WHERE " + Transaction.COLUMN_BEVERAGE_ID + " = ?";
            args = new String[]{String.valueOf(beverageId)};
        } else {
            return false;
        }
        
        try (Cursor cursor = db.rawQuery(query, args)) {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0) > 0;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking for transactions", e);
        }
        
        return false;
    }
    
    // ==== CURSOR TO OBJECT CONVERTERS ====
    
    /**
     * Convert cursor to Member object
     */
    private Member cursorToMember(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(Member.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(Member.COLUMN_NAME));
        
        int gridPosIndex = cursor.getColumnIndexOrThrow(Member.COLUMN_GRID_POSITION);
        Integer gridPosition = cursor.isNull(gridPosIndex) ? null : cursor.getInt(gridPosIndex);
        
        long createdAt = cursor.getLong(cursor.getColumnIndexOrThrow(Member.COLUMN_CREATED_AT));
        
        return new Member(id, name, gridPosition, createdAt);
    }
    
    /**
     * Convert cursor to Beverage object
     */
    private Beverage cursorToBeverage(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(Beverage.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(Beverage.COLUMN_NAME));
        double price = cursor.getDouble(cursor.getColumnIndexOrThrow(Beverage.COLUMN_PRICE));
        
        int categoryIndex = cursor.getColumnIndexOrThrow(Beverage.COLUMN_CATEGORY);
        String category = cursor.isNull(categoryIndex) ? null : cursor.getString(categoryIndex);
        
        boolean active = cursor.getInt(cursor.getColumnIndexOrThrow(Beverage.COLUMN_ACTIVE)) == 1;
        
        return new Beverage(id, name, price, category, active);
    }
    
    /**
     * Convert cursor to Transaction object
     */
    private Transaction cursorToTransaction(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(Transaction.COLUMN_ID));
        long memberId = cursor.getLong(cursor.getColumnIndexOrThrow(Transaction.COLUMN_MEMBER_ID));
        long beverageId = cursor.getLong(cursor.getColumnIndexOrThrow(Transaction.COLUMN_BEVERAGE_ID));
        int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(Transaction.COLUMN_QUANTITY));
        double unitPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(Transaction.COLUMN_UNIT_PRICE));
        double totalPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(Transaction.COLUMN_TOTAL_PRICE));
        long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(Transaction.COLUMN_TIMESTAMP));
        
        return new Transaction(id, memberId, beverageId, quantity, unitPrice, totalPrice, timestamp);
    }
    
    /**
     * Convert cursor to TransactionWithDetails object (for joined queries)
     */
    private TransactionWithDetails cursorToTransactionWithDetails(Cursor cursor) {
        long transactionId = cursor.getLong(0);  // t.id
        long memberId = cursor.getLong(1);       // t.member_id
        String memberName = cursor.getString(2); // m.name
        long beverageId = cursor.getLong(3);     // t.beverage_id
        String beverageName = cursor.getString(4); // b.name
        int quantity = cursor.getInt(5);         // t.quantity
        double unitPrice = cursor.getDouble(6);  // t.unit_price
        double totalPrice = cursor.getDouble(7); // t.total_price
        long timestamp = cursor.getLong(8);      // t.timestamp
        
        return new TransactionWithDetails(transactionId, memberId, memberName,
                                        beverageId, beverageName, quantity,
                                        unitPrice, totalPrice, timestamp);
    }
}