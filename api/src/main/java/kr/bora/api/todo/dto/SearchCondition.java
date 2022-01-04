package kr.bora.api.todo.dto;

public class SearchCondition {
    private String content;
    private SearchType type;

    public SearchCondition(String content, SearchType type) {
        this.content = content;
        this.type = type;
    }
}

