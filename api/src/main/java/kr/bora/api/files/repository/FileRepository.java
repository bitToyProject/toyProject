package kr.bora.api.files.repository;

import io.swagger.v3.oas.annotations.Parameter;
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
    List<Files> findByLocalFileId(@Param("fileId") Long fileId);


    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileId in (:fileId) and f.fileType=:fileType and f.todoId =:todoId")
    void todoFilesDelete(@Param("fileId") Long[] fileId, @Param("fileType") FileType fileType, @Param("todoId") Long todoId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileId in (:fileId) and f.fileType=:fileType and f.textId =:textId")
    void textFilesDelete(@Param("fileId") Long[] fileId, @Param("fileType") FileType fileType, @Param("textId") Long textId);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Files f where f.fileType='LOCAL' AND f.fileId IN (:fileId)")
    void localFilesDelete(@Param("fileId") Long fileId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn = 'Y', f.modDate=current_timestamp where f.fileId IN (:fileId) and f.fileType=:fileType and f.todoId =:todoId")
    void todoFilesUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType, @Param("todoId") Long todoId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn = 'Y', f.modDate=current_timestamp where f.fileId IN (:fileId) and f.fileType=:fileType and f.textId =:textId")
    void textFilesUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType, @Param("textId") Long textId);

    @Modifying
    @Transactional
    @Query("UPDATE Files f SET f.deleteYn='Y', f.modDate=current_timestamp where f.fileId IN(:fileId) and f.fileType=:fileType")
    void localFileUpdate(@Param("fileId") Long fileId, @Param("fileType") FileType fileType);

    @Query("SELECT f.filename from Files f where f.fileId=:fileId and f.fileType=:fileType and f.deleteYn='N'")
    String findByFileIdAndFileType(@Param("fileId") Long fileId, @Param("fileType") FileType fileType);

    @Query("SELECT f.user.nickName from Files f where f.fileId IN(:fileId) and f.fileType='LOCAL'")
    String getLocalFileUploader(@Param("fileId") Long fileId);
}
