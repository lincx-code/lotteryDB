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
@RequestMapping("/lotto/*")
public class LottoController {
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

	/* Get all lotto records *********************************** */
	@RequestMapping(value = GET_COLLECTION, method = RequestMethod.GET)
	public ModelAndView getCollection(final HttpServletRequest request) {

		int pageNum = 1;
		if (request.getParameter("pg") != null && Utility.isNumeric(request.getParameter("pg"))) {
			pageNum = Integer.parseInt(request.getParameter("pg"));
		}

		try {
			LottoDAO lottoDAO = new LottoDAO(dataSource);
			int totalRecords = lottoDAO.getDataTotal();
			List<Lotto> collection = lottoDAO.getLottoCollection(pageNum);
			int pages = (int) Math.ceil((double) totalRecords / Constants.DEFAULT_DATA_PER_PAGE);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("pages", pages);
			model.put("current", pageNum);
			model.put("collection", collection);
			return new ModelAndView("lottery/lotto_list", model);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to retrieve collection from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* Get existing lotto record *********************************** */
	@RequestMapping(value = GET_DATA, method = RequestMethod.GET)
	public ModelAndView getData(@PathVariable("id") int id) {

		try {
			LottoDAO lottoDAO = new LottoDAO(dataSource);
			Lotto lotto = lottoDAO.getLotto(id);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("lotto", lotto);
			return new ModelAndView("lottery/lotto_data", model);
		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to retrieve data from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* redirect to insert page *********************************** */
	@RequestMapping(value = NEW, method = RequestMethod.GET)
	public String doNew() {
		return "lottery/lotto_new";
	}

	/* insert new lotto record *********************************** */
	@RequestMapping(value = INSERT, method = RequestMethod.POST)
	public ModelAndView insertData(@RequestParam Map<String, String> requestParams) {

		try {
			/* draw_date, num1, num2, num3, num4, num5, num6 */
			Lotto lotto = new Lotto(Utility.strToDate(requestParams.get("draw_date")),
					Integer.parseInt(requestParams.get("num1")), Integer.parseInt(requestParams.get("num2")),
					Integer.parseInt(requestParams.get("num3")), Integer.parseInt(requestParams.get("num4")),
					Integer.parseInt(requestParams.get("num5")), Integer.parseInt(requestParams.get("num6")));

			LottoDAO lottoDAO = new LottoDAO(dataSource);
			lottoDAO.insertLotto(lotto);

			return new ModelAndView("redirect:/lotto/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to insert data to the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* update existing lotto record *********************************** */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public ModelAndView updateData(@PathVariable("id") int id, @RequestParam Map<String, String> requestParams) {

		try {
			/* draw_date, num1, num2, num3, num4, num5, num6 */
			Lotto lotto = new Lotto(id, Utility.strToDate(requestParams.get("draw_date")),
					Integer.parseInt(requestParams.get("num1")), Integer.parseInt(requestParams.get("num2")),
					Integer.parseInt(requestParams.get("num3")), Integer.parseInt(requestParams.get("num4")),
					Integer.parseInt(requestParams.get("num5")), Integer.parseInt(requestParams.get("num6")));

			LottoDAO lottoDAO = new LottoDAO(dataSource);
			lottoDAO.updateLotto(lotto);

			return new ModelAndView("redirect:/lotto/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to update data in the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* delete existing lotto record *********************************** */
	@RequestMapping(value = DELETE, method = RequestMethod.GET)
	public ModelAndView deleteData(@PathVariable("id") int id) {

		try {
			LottoDAO lottoDAO = new LottoDAO(dataSource);
			lottoDAO.deleteLotto(id);

			return new ModelAndView("redirect:/lotto/collection");

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to delete data from the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}

	/* computer generate numbers *********************************** */
	@RequestMapping(value = GENERATE, method = RequestMethod.GET)
	public ModelAndView generate() {

		int[] multiBalls = Utility.nextRandomArray(6, Constants.LOTTO_MULTI);

		/*
		 * for (int i = 0; i < multiBalls.length; i++) { System.out.print(multiBalls[i] + " "); }
		 */

		Lotto lotto = new Lotto();
		lotto.setNum1(multiBalls[0]);
		lotto.setNum2(multiBalls[1]);
		lotto.setNum3(multiBalls[2]);
		lotto.setNum4(multiBalls[3]);
		lotto.setNum5(multiBalls[4]);
		lotto.setNum6(multiBalls[5]);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("lotto", lotto);
		return new ModelAndView("lottery/lotto_generate", model);
	}

	/* generate number statistics *********************************** */
	@RequestMapping(value = STAT, method = RequestMethod.GET)
	public ModelAndView doReport() {

		try {
			LottoDAO lottoDAO = new LottoDAO(dataSource);
			List<OccurrenceStat> multiStat = lottoDAO.doOccurrenceReport(Constants.DEFAULT_REPORT_ROWS);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("multiStat", multiStat);
			return new ModelAndView("lottery/lotto_stat", model);

		} catch (Exception e) {
			ErrorLog error = new ErrorLog(e, "Failed to update data in the database.");
			error.printErrorLog();
			return new ModelAndView(Constants.ERROR_DATABASE);
		}
	}
}
