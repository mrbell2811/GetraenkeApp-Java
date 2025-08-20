package com.club.getraenkeapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.club.getraenkeapp.data.database.entities.Member

/**
 * Data Access Object for Member operations.
 * 
 * Provides CRUD operations for members with reactive LiveData queries
 * and suspend functions for background operations.
 */
@Dao
interface MemberDao {
    
    @Query("SELECT * FROM members ORDER BY grid_position ASC, name ASC")
    fun getAllMembers(): LiveData<List<Member>>
    
    @Query("SELECT * FROM members WHERE id = :id")
    suspend fun getMemberById(id: Long): Member?
    
    @Query("SELECT * FROM members WHERE name = :name LIMIT 1")
    suspend fun getMemberByName(name: String): Member?
    
    @Query("SELECT COUNT(*) FROM members")
    suspend fun getMemberCount(): Int
    
    @Query("SELECT MAX(grid_position) FROM members")
    suspend fun getMaxGridPosition(): Int?
    
    @Insert
    suspend fun insertMember(member: Member): Long
    
    @Update
    suspend fun updateMember(member: Member)
    
    @Delete
    suspend fun deleteMember(member: Member)
    
    @Query("DELETE FROM members WHERE id = :id")
    suspend fun deleteMemberById(id: Long)
    
    /**
     * Check if member has any transactions before allowing deletion
     */
    @Query("SELECT COUNT(*) FROM transactions WHERE member_id = :memberId")
    suspend fun getTransactionCountForMember(memberId: Long): Int
    
    /**
     * Update grid positions for layout configuration
     */
    @Query("UPDATE members SET grid_position = :position WHERE id = :memberId")
    suspend fun updateMemberGridPosition(memberId: Long, position: Int)
}