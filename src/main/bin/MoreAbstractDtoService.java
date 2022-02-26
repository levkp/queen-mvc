//package prog21assignment.service;
//
//import prog21assignment.presentation.dto.QueenEntityDto;
//
//import java.util.List;
//
//public interface NewService<T extends QueenEntityDto> {
//    T create(T t);
//    List<T> read();
//
//    default T updateById(int id, T t) {
//        return t;
//    }
//
//    default void delete(int id) {
//
//    }
//
//    T findById(int id);
//}
