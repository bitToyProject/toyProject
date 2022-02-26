package kr.bora.api.todo.repository.search;

import kr.bora.api.todo.domain.Todo;
import kr.bora.api.todo.dto.searchPageDto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchTodoRepository {

    public List<Todo> search(SearchCondition searchCondition);

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);


}
