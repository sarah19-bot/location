package sn.diallo.repository.jdbc;

import java.sql.Connection;

public interface DataSource {
    Connection createConnection();
}
