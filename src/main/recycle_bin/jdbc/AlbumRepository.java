package prog21assignment.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import prog21assignment.domain.Album;
import prog21assignment.repository.QueenEntityRepository;

import java.sql.PreparedStatement;
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
        this.inserter = new SimpleJdbcInsert(template)
                .withTableName("album")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Album create(Album album) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                    "INSERT INTO album(id, title, release, description)" +
                        "VALUES(NEXT VALUE FOR entity_pk_seq, ?, ?, ?)");



        /*
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", album.getTitle());
        parameters.put("release", album.getRelease());
        parameters.put("description", album.getDescription());
        album.setId(inserter.executeAndReturnKey(parameters).intValue());
        */
        return album;
    }

    @Override
    public List<Album> read() {
        return template.query("select * from album", this::mapRow);
    }

    @Override
    public Album findById(int id) {
        return template.queryForObject("select * from album where id = ?", this::mapRow, id);
    }

    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        Album album = new Album(
                rs.getString("title"),
                rs.getDate("release").toLocalDate(),
                rs.getString("description"));
        album.setId(rs.getInt("id"));
        return album;
    }
}
