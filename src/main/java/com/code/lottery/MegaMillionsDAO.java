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

public class MegaMillionsDAO {
	// Spring MVC JDBC Template and data source configuration
	private final JdbcTemplate jdbcTemplate;

	// Constructor
	public MegaMillionsDAO(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Get total mega millions collection record count **********
	public int getDataTotal() {
		String queryStmt = "SELECT COUNT(*) FROM vw_mega_millions";
		int total = 0;

		try {
			total = jdbcTemplate.queryForObject(queryStmt, Integer.class);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, queryStmt);
			error.printErrorLog();
		}
		return total;
	}

	// Get Mega Milliions collection ****************************
	public List<MegaMillions> getMegaCollection(int pageNum) {

		// Important! Page number we have here is always 1 more than the actual number due to user readability issue
		// It's not readable if page number is 0
		int rangeStart = (pageNum - 1) * Constants.DEFAULT_DATA_PER_PAGE;
		int rangeEnd = rangeStart + Constants.DEFAULT_DATA_PER_PAGE;

		String queryStmt = "SELECT * FROM vw_mega_millions LIMIT " + rangeStart + ", " + rangeEnd;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<List<MegaMillions>>() {
			@Override
			public List<MegaMillions> extractData(final ResultSet rs) throws SQLException, DataAccessException {

				ArrayList<MegaMillions> megaMillions = new ArrayList<MegaMillions>();
				while (rs.next()) {
					/* id, draw_date, ball1, ball2, ball3, ball4, ball5, megaball */
					MegaMillions mega = new MegaMillions(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("ball1"),
							rs.getInt("ball2"), rs.getInt("ball3"), rs.getInt("ball4"), rs.getInt("ball5"),
							rs.getInt("megaball"));

					megaMillions.add(mega);
				}
				return megaMillions;
			}
		});
	}

	// Get MegaMillions by id *************************
	public MegaMillions getMegaMillions(int id) {

		String queryStmt = "SELECT * FROM vw_mega_millions WHERE id = " + id;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<MegaMillions>() {

			@Override
			public MegaMillions extractData(final ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					/* id, draw_date, ball1, ball2, ball3, ball4, ball5, megaball */
					MegaMillions mega = new MegaMillions(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("ball1"),
							rs.getInt("ball2"), rs.getInt("ball3"), rs.getInt("ball4"), rs.getInt("ball5"),
							rs.getInt("megaball"));

					return mega;
				} else {
					return null;
				}
			}
		});
	}

	// Insert new MegaMillions *************************
	public int insertMegaMillions(MegaMillions mega) {
		String queryStmt = "INSERT INTO mega_millions (draw_date, ball1, ball2, ball3, ball4, ball5, megaball) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			int count = jdbcTemplate.update(queryStmt, new Object[] { mega.getDrawDate(), mega.getBall1(),
					mega.getBall2(), mega.getBall3(), mega.getBall4(), mega.getBall5(), mega.getMegaball() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Update MegaMillions ***************************
	public int updateMegaMillions(MegaMillions mega) {
		String queryStmt = "Update mega_millions SET draw_date = ?, ball1 = ?, ball2 = ?, ball3 = ?, ball4 = ?, ball5 = ?, "
				+ "megaball = ? WHERE id = ?";

		try {
			int count = jdbcTemplate.update(queryStmt,
					new Object[] { mega.getDrawDate(), mega.getBall1(), mega.getBall2(), mega.getBall3(),
							mega.getBall4(), mega.getBall5(), mega.getMegaball(), mega.getId() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Delete MegaMillions *************************
	public int deleteMegaMillions(int id) {
		String queryStmt = "DELETE FROM mega_millions WHERE id = ?";

		try {
			int count = jdbcTemplate.update(queryStmt, new Object[] { id });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Get the most frequent numbers from database (multiple true for pick 5, false for pick 1)
	public List<OccurrenceStat> doOccurrenceReport(boolean multiple, int max_record) {

		String queryStmt = "";

		if (multiple == true) {
			queryStmt = "SELECT * FROM vw_mega_millions_occ LIMIT " + max_record;
		} else {
			queryStmt = "SELECT * FROM vw_mega_millions_occ_single LIMIT " + max_record;
		}

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
