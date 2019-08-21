package com.code.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.code.common.log.ErrorLog;
import com.code.common.utility.Constants;
import com.code.common.utility.Utility;

@Controller
@RequestMapping("/powerball/*")
public class PowerballController {

	/* REQUEST URI */
	public static final String GET_COLLECTION = "collection";
	public static final String GET_DATA = "{id}";
	public static final String NEW = "new";
	public static final String INSERT = "insert";
	public static final String UPDATE = "update/{id}";
	public static final String DELETE = "delete/{id}";
	public static final String GENERATE = "generate";
	public static final String STAT = "stat";

	private DataSource dataSource;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/* Get all powerball records *********************************** */
	@RequestMapping(value = GET_COLLECTION, method = RequestMethod.GET)
	public ModelAndView getCollection(final HttpServletRequest request) {

		int pageNum = 1;
		if (request.getParameter("pg") != null && Utility.isNumeric(request.getParameter("pg"))) {
			pageNum = Integer.parseInt(request.getParameter("pg"));
		}

		try {
			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			int totalRecords = powerballDAO.getDataTotal();
			List<Powerball> collection = powerballDAO.getPowerballCollection(pageNum);
			int pages = (int) Math.ceil((double) totalRecords / Constants.DEFAULT_DATA_PER_PAGE);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("pages", pages);
			model.put("current", pageNum);
			model.put("collection", collection);
			return new ModelAndView("lottery/powerball_list", model);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to retrieve collection from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* Get existing powerball record *********************************** */
	@RequestMapping(value = GET_DATA, method = RequestMethod.GET)
	public ModelAndView getData(@PathVariable("id") int id) {

		try {
			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			Powerball powerball = powerballDAO.getPowerball(id);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("powerball", powerball);
			return new ModelAndView("lottery/powerball_data", model);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to retrieve data from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* redirect to insert page *********************************** */
	@RequestMapping(value = NEW, method = RequestMethod.GET)
	public String doNew() {
		return "lottery/powerball_new";
	}

	/* insert new powerball record *********************************** */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	public ModelAndView insertData(@RequestParam Map<String, String> requestParams) {

		try {
			/* draw_date, ball1, ball2, ball3, ball4, ball5, powerball */
			Powerball powerball = new Powerball(Utility.strToDate(requestParams.get("draw_date")),
					Integer.parseInt(requestParams.get("ball1")), Integer.parseInt(requestParams.get("ball2")),
					Integer.parseInt(requestParams.get("ball3")), Integer.parseInt(requestParams.get("ball4")),
					Integer.parseInt(requestParams.get("ball5")), Integer.parseInt(requestParams.get("powerball")));

			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			powerballDAO.insertPowerball(powerball);

			return new ModelAndView("redirect:/powerball/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to insert data to the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* update existing powerball record *********************************** */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public ModelAndView updateData(@PathVariable("id") int id, @RequestParam Map<String, String> requestParams) {

		try {
			/* id, draw_date, ball1, ball2, ball3, ball4, ball5, powerball */
			Powerball powerball = new Powerball(id, Utility.strToDate(requestParams.get("draw_date")),
					Integer.parseInt(requestParams.get("ball1")), Integer.parseInt(requestParams.get("ball2")),
					Integer.parseInt(requestParams.get("ball3")), Integer.parseInt(requestParams.get("ball4")),
					Integer.parseInt(requestParams.get("ball5")), Integer.parseInt(requestParams.get("powerball")));

			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			powerballDAO.updatePowerball(powerball);

			return new ModelAndView("redirect:/powerball/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to update data in the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* delete existing powerball record *********************************** */
	@RequestMapping(value = DELETE, method = RequestMethod.GET)
	public ModelAndView deleteData(@PathVariable("id") int id) {

		try {
			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			powerballDAO.deletePowerball(id);

			return new ModelAndView("redirect:/powerball/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to delete data from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* computer generate numbers *********************************** */
	@RequestMapping(value = GENERATE, method = RequestMethod.GET)
	public ModelAndView generate() {

		int[] multiBalls = Utility.nextRandomArray(5, Constants.POWERBALL_MULTI);

		/*
		 * for (int i = 0; i < multiBalls.length; i++) { System.out.print(multiBalls[i] + " "); }
		 */

		Powerball powerball = new Powerball();
		powerball.setBall1(multiBalls[0]);
		powerball.setBall2(multiBalls[1]);
		powerball.setBall3(multiBalls[2]);
		powerball.setBall4(multiBalls[3]);
		powerball.setBall5(multiBalls[4]);
		powerball.setPowerball(Utility.nextRandom(1, Constants.POWERBALL_SINGLE));

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("powerball", powerball);
		return new ModelAndView("lottery/powerball_generate", model);
	}

	/* generate number statistics *********************************** */
	@RequestMapping(value = STAT, method = RequestMethod.GET)
	public ModelAndView doReport() {

		try {
			PowerballDAO powerballDAO = new PowerballDAO(dataSource);
			List<OccurrenceStat> multiStat = powerballDAO.doOccurrenceReport(true, Constants.DEFAULT_REPORT_ROWS);
			List<OccurrenceStat> singleStat = powerballDAO.doOccurrenceReport(false, Constants.DEFAULT_REPORT_ROWS);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("multiStat", multiStat);
			model.put("singleStat", singleStat);
			return new ModelAndView("lottery/powerball_stat", model);

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to update data in the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}
}
