package kr.bora.api.texteditor.repository;

import kr.bora.api.texteditor.domain.entity.TextEditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TextEditorRepository extends JpaRepository<TextEditor, Long> {

    @Query("SELECT te, w FROM TextEditor te LEFT JOIN te.user w where te.textEditId = :id")
    TextEditor getTextEditor(@Param("id") Long id);


    /**
     * 사용자 삭제 시 TextEditor 데이터 삭제
     * @param userId
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM TextEditor te where te.user.userId=:userId")
    void deleteByUserId(@Param("userId") Long userId);
}
