package kr.bora.api.files.repository;

import kr.bora.api.files.domain.FileType;
import kr.bora.api.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileRepository extends JpaRepository<Files, Long> {

    //    @Query("select f.todo.todoId from Files f where f.todo.todoId =: todoId")
//    Long findByTodoId(@Param("todoId") Long todoId);
//

    @Query("SELECT f from Files f where f.todoId=:todoId and f.fileType =:fileType and f.deleteYn ='N'")
    List<Files> findByTodoFileId(@Param("fileType") FileType fileType, @Param("todoId") Long todoId);

    @Query("SELECT f from Files f where f.textId=:textId and f.fileType =:fileType and f.deleteYn ='N'")
    List<Files> findByTextFileId(@Param("fileType") FileType fileType, @Param("textId") Long textId);

    @Query("SELECT f from Files f where f.fileId=:fileId and f.fileType ='LOCAL' and f.deleteYn ='N'")
    List<Files> findByFileId(@Param("fileId") Long fileId);

//    @Modifying(clearAutomatically = true)
//    @Query("DELETE FROM Files f where f.fileId in (:fileId) and f.fileType=:fileType and f.todoId =:todoId")
//    void todoFilesDelete(@Param("fileId") Long[] fileId, @Param("fileType") FileType fileType, @Param("todoId") Long todoId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileType='TEXT_EDITOR'and f.textId =:textId") // 텍스터 에디터의 게시글이 사라지면 완전삭제
    void textFilesDelete(@Param("textId") Long textId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileType='TODO'and f.todoId =:todoId") // Todo 게시글이 사라지면 완전삭제
    void todoFilesDelete(@Param("todoId") Long todoId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileType='LOCAL'and f.fileId IN (:fileId)")
    void localFilesDelete(@Param("fileId") Long fileId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn = 'Y' where f.fileId IN (:fileId) and f.fileType=:fileType and f.todoId =:todoId")
    void todoFilesUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType, @Param("todoId") Long todoId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn = 'Y', f.modDate=current_timestamp where f.fileId IN (:fileId) and f.fileType=:fileType and f.textId =:textId")
    void textFilesUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType, @Param("textId") Long textId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn = 'Y', f.modDate=current_timestamp where f.fileId IN (:fileId) and f.fileType=:fileType")
    void localFilesUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType);


    @Query("SELECT f.filename from Files f where f.fileId=:fileId and f.fileType=:fileType and f.deleteYn='N'")
    String findByFileIdAndFileType(@Param("fileId") Long fileId, @Param("fileType") FileType fileType);

    @Query("select f.user.nickName from Files f where f.fileId IN(:fileId) and f.fileType='LOCAL'")
    String getLocalFileUploader(@Param("fileId") Long fileId);


}
