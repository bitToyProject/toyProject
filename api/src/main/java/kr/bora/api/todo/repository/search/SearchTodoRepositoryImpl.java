package kr.bora.api.todo.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.bora.api.todo.domain.QTodo;
import kr.bora.api.todo.domain.Todo;
import kr.bora.api.user.domain.QUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchTodoRepositoryImpl extends QuerydslRepositorySupport implements SearchTodoRepository {

    public SearchTodoRepositoryImpl() {
        super(Todo.class);
    }

    @Override
    public Todo search() {
        log.info("이게 서치인가");

        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        JPQLQuery<Todo> jpqlQuery = from(todo);
        jpqlQuery.leftJoin(user).on(todo.user.eq(user));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(todo, user.nickName, user.username);
        tuple.groupBy(todo);

        log.info("======================");
        log.info(tuple);
        log.info("======================");

        List<Tuple> result = tuple.fetch();

        log.info(result);

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {

        log.info("searchPage");

        QTodo todo = QTodo.todo;
        QUser user = QUser.user;

        JPQLQuery<Todo> jpqlQuery = from(todo);
        jpqlQuery.leftJoin(user).on(todo.user.eq(user));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(todo, user);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = todo.todoId.gt(0L);

        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");

            log.info(Arrays.toString(typeArr));

            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(todo.title.contains(keyword));
                        break;
                    case "d":
                        conditionBuilder.or(todo.description.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(user.username.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        //order by
        Sort sort = pageable.getSort();

        sort.stream().forEach(order ->{
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Todo.class, "todo");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(todo);

        //page 처리
        log.info(pageable.getOffset());
        log.info(pageable.getPageSize());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("count : " + count);

        return new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()), pageable, count);
    }
}
