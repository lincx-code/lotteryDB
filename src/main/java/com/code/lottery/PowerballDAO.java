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

public class PowerballDAO {
	// Spring MVC JDBC Template and data source configuration
	private final JdbcTemplate jdbcTemplate;

	// Constructor
	public PowerballDAO(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// Get total Powerball collection record count **********
	public int getDataTotal() {
		String queryStmt = "SELECT COUNT(*) FROM vw_powerball";
		int total = 0;

		try {
			total = jdbcTemplate.queryForObject(queryStmt, Integer.class);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, queryStmt);
			error.printErrorLog();
		}
		return total;
	}

	// Get Powerball collection ****************************
	public List<Powerball> getPowerballCollection(int pageNum) {
		// Important! Page number we have here is always 1 more than the actual number due to user readability issue
		// It's not readable if page number is 0
		int rangeStart = (pageNum - 1) * Constants.DEFAULT_DATA_PER_PAGE;
		int rangeEnd = rangeStart + Constants.DEFAULT_DATA_PER_PAGE;

		String queryStmt = "SELECT * FROM vw_powerball LIMIT " + rangeStart + ", " + rangeEnd;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<List<Powerball>>() {
			@Override
			public List<Powerball> extractData(final ResultSet rs) throws SQLException, DataAccessException {

				ArrayList<Powerball> powerballs = new ArrayList<Powerball>();
				while (rs.next()) {
					/* id, draw_date, ball1, ball2, ball3, ball4, ball5, powerball */
					Powerball powerball = new Powerball(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("ball1"),
							rs.getInt("ball2"), rs.getInt("ball3"), rs.getInt("ball4"), rs.getInt("ball5"),
							rs.getInt("powerball"));

					powerballs.add(powerball);
				}
				return powerballs;
			}
		});
	}

	// Get Powerball by id *************************
	public Powerball getPowerball(int id) {

		String queryStmt = "SELECT * FROM vw_powerball WHERE id = " + id;

		return jdbcTemplate.query(queryStmt, new ResultSetExtractor<Powerball>() {

			@Override
			public Powerball extractData(final ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					/* id, draw_date, ball1, ball2, ball3, ball4, ball5, powerball */
					Powerball powerball = new Powerball(rs.getInt("id"), rs.getDate("draw_date"), rs.getInt("ball1"),
							rs.getInt("ball2"), rs.getInt("ball3"), rs.getInt("ball4"), rs.getInt("ball5"),
							rs.getInt("powerball"));

					return powerball;
				} else {
					return null;
				}
			}
		});
	}

	// Insert new Powerball *************************
	public int insertPowerball(Powerball powerball) {
		String queryStmt = "INSERT INTO powerball (draw_date, ball1, ball2, ball3, ball4, ball5, powerball) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			int count = jdbcTemplate.update(queryStmt,
					new Object[] { powerball.getDrawDate(), powerball.getBall1(), powerball.getBall2(),
							powerball.getBall3(), powerball.getBall4(), powerball.getBall5(),
							powerball.getPowerball() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Update Powerball ***************************
	public int updatePowerball(Powerball powerball) {
		String queryStmt = "Update powerball SET draw_date = ?, ball1 = ?, ball2 = ?, ball3 = ?, ball4 = ?, ball5 = ?, "
				+ "powerball = ? WHERE id = ?";

		try {
			int count = jdbcTemplate.update(queryStmt,
					new Object[] { powerball.getDrawDate(), powerball.getBall1(), powerball.getBall2(),
							powerball.getBall3(), powerball.getBall4(), powerball.getBall5(), powerball.getPowerball(),
							powerball.getId() });

			return count;
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "[SQL] " + queryStmt);
			error.printErrorLog();
			throw e;
		}
	}

	// Delete Powerball *************************
	public int deletePowerball(int id) {
		String queryStmt = "DELETE FROM powerball WHERE id = ?";

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
			queryStmt = "SELECT * FROM vw_powerball_occ LIMIT " + max_record;
		} else {
			queryStmt = "SELECT * FROM vw_powerball_occ_single LIMIT " + max_record;
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
