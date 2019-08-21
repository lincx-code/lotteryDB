package com.code.lottery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.code.common.log.ErrorLog;
import com.code.common.utility.Constants;

public class LottoDAO {

	// Spring MVC JDBC Template and data source configuration
	private final JdbcTemplate jdbcTemplate;

	// Constructor
	public LottoDAO(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Get total Powerball collection record count **********
	public int getDataTotal() {
		String queryStmt = "SELECT COUNT(*) FROM vw_lotto";
		int total = 0;

		try {
			total = jdbcTemplate.queryForObject(queryStmt, Integer.class);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, queryStmt);
			error.printErrorLog();
		}
		return total;
	}

	// Get Lotto collection ****************************
	public List<Lotto> getLottoCollection(int pageNum) {

		// Important! Page number we have here is always 1 more than the actual number due to user readability issue
		// It's not readable if page number is 0
		int rangeStart = (pageNum - 1) * Constants.DEFAULT_DATA_PER_PAGE;
		int rangeEnd = rangeStart + Constants.DEFAULT_DATA_PER_PAGE;

		String queryStmt = "SELECT * FROM vw_lotto LIMIT " + rangeStart + ", " + rangeEnd;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<List<Lotto>>() {
			@Override
			public List<Lotto> extractData(final ResultSet rs) throws SQLException, DataAccessException {

				ArrayList<Lotto> lottos = new ArrayList<Lotto>();
				while (rs.next()) {
					/* id, draw_date, num1, num2, num3, num4, num5, num6, bonus */
					Lotto lotto = new Lotto(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("num1"),
							rs.getInt("num2"), rs.getInt("num3"), rs.getInt("num4"), rs.getInt("num5"),
							rs.getInt("num6"));

					lottos.add(lotto);
				}
				return lottos;
			}
		});
	}

	// Get lotto by id *************************
	public Lotto getLotto(int id) {

		String queryStmt = "SELECT * FROM vw_lotto WHERE id = " + id;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<Lotto>() {

			@Override
			public Lotto extractData(final ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					/* id, draw_date, num1, num2, num3, num4, num5, num6, bonus */
					Lotto lotto = new Lotto(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("num1"),
							rs.getInt("num2"), rs.getInt("num3"), rs.getInt("num4"), rs.getInt("num5"),
							rs.getInt("num6"));

					return lotto;
				} else {
					return null;
				}
			}
		});
	}

	// Insert new lotto *************************
	public int insertLotto(Lotto lotto) {
		String queryStmt = "INSERT INTO lotto (draw_date, num1, num2, num3, num4, num5, num6) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			int count = jdbcTemplate.update(queryStmt, new Object[] { lotto.getDrawDate(), lotto.getNum1(),
					lotto.getNum2(), lotto.getNum3(), lotto.getNum4(), lotto.getNum5(), lotto.getNum6() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Update lotto ***************************
	public int updateLotto(Lotto lotto) {
		String queryStmt = "Update lotto SET draw_date = ?, num1= ?, num2 = ?, num3 = ?, num4 = ?, num5 = ?, "
				+ "num6 = ? WHERE id = ?";

		try {
			int count = jdbcTemplate.update(queryStmt,
					new Object[] { lotto.getDrawDate(), lotto.getNum1(), lotto.getNum2(), lotto.getNum3(),
							lotto.getNum4(), lotto.getNum5(), lotto.getNum6(), lotto.getId() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Delete lotto *************************
	public int deleteLotto(int id) {
		String queryStmt = "DELETE FROM lotto WHERE id = ?";

		try {
			int count = jdbcTemplate.update(queryStmt, new Object[] { id });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Get the most frequent numbers from database
	public List<OccurrenceStat> doOccurrenceReport(int max_record) {

		String queryStmt = "SELECT * FROM vw_lotto_occ LIMIT " + max_record;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<List<OccurrenceStat>>() {
			@Override
			public List<OccurrenceStat> extractData(final ResultSet rs) throws SQLException, DataAccessException {

				ArrayList<OccurrenceStat> occurrences = new ArrayList<OccurrenceStat>();
				while (rs.next()) {
					OccurrenceStat occurrence = new OccurrenceStat(rs.getInt("number"), rs.getInt("count"));
					occurrences.add(occurrence);
				}
				return occurrences;
			}
		});
	}
}
