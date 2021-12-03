package prog21assignment.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("jdbc")
@Repository
public class AlbumRepository implements QueenEntityRepository<Album> {

    private final JdbcTemplate template;
    private final SimpleJdbcInsert inserter;

    public AlbumRepository(JdbcTemplate template) {
        this.template = template;
        inserter = new SimpleJdbcInsert(template).withTableName("ALBUM").usingGeneratedKeyColumns("ID");
    }

    @Override
    public Album create(Album a) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITLE", a.getTitle());
        parameters.put("RELEASE", Date.valueOf(a.getRelease()));
        parameters.put("DESCRIPTION", Date.valueOf(a.getDescription()));
        a.setId(inserter.executeAndReturnKey(parameters).intValue());
        return a;
    }

    @Override
    public List<Album> read() {
        return null;
    }

    @Override
    public void update(Album a) {

    }

    @Override
    public void delete(Album a) {

    }

    @Override
    public Album findById(int id) {
        return null;
//        return jdbc.queryForObject("SELECT * FROM ALBUM WHERE ID = ?", null);
    }

    public Album mapRow(ResultSet rs, int row) throws SQLException {
        Album a = new Album(
                rs.getString("TITLE"), rs.getDate("RELEASE").toLocalDate(), rs.getString("DESCRIPTION"));
        a.setId(rs.getInt("ID"));
        return a;
    }
}
