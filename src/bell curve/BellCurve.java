
package bellcurve;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import security.Decryption;
import static org.junit.experimental.ParallelComputer.methods;

/**
 * This demo shows a normal distribution function.
 */
/**
 * Java class for Bell Curve generation
 * @author GreyHood
 *
 */
public class BellCurve extends ApplicationFrame {
	Decryption de = new Decryption();

	/**
	 * A demonstration application showing a normal distribution.
	 *
	 * 
	 */
	/**
	 * @param title Title of the frame on which bell curve will be displayed
	 * @throws SQLException  Any exception while accessing database
	 */
	public BellCurve(final String title) throws SQLException {

		super(title);
		String otp = null;
		String temp = null;

		try {
			Class.forName("nl.cwi.monetdb.jdbc.MonetDriver");
			Connection con = DriverManager.getConnection("jdbc:monetdb://localhost:50000/", "monetdb", "monetdb");
			double[] myList = new double[9];
			double[] myList1 = new double[9];
			String band = "null";
			double sum = 0;
			PreparedStatement ps, ps1;
			ps = con.prepareStatement("SELECT finalrating,otp FROM ratings");
			ResultSet rs;
			rs = ps.executeQuery();
			int j = 0;
			while (rs.next()) {
				otp = rs.getString(2);
				temp = de.decrypt(rs.getString(1), otp);
				System.out.println(temp);
				myList[j] = Double.parseDouble(temp);
				j++;
			}
			System.out.println(Arrays.toString(myList));

			for (int i = 0; i < myList.length; i++) {
				sum = sum + myList[i];
			}
			// ArrayList al = new ArrayList();
			System.out.println("" + sum);
			double mean = (sum / myList.length);
			System.out.println("" + mean);

			for (int i = 0; i < myList.length; i++) {
				myList1[i] = myList[i] - mean;
				System.out.println(myList1[i]);
			}
			for (int i = 0; i < myList1.length; i++)
				myList1[i] = myList1[i] * myList1[i];
			double variance = 0;
			for (int i = 0; i < myList1[i]; i++)
				variance = variance + myList1[i];
			System.out.println(variance);
			variance = (variance / myList1.length);
			System.out.println(variance);
			double sd = Math.sqrt(variance);
			System.out.println(+sd);
			XYSeriesCollection xyseriescollection = new XYSeriesCollection();
			Function2D normal = new NormalDistributionFunction2D(mean, sd);
			org.jfree.data.xy.XYSeries xyseries = DatasetUtilities.sampleFunction2DToSeries(normal, (mean - 1 * sd),
					(mean + 1 * sd), 100, "Satisfactory");
			xyseriescollection.addSeries((xyseries));
			Function2D normal1 = new NormalDistributionFunction2D(mean, sd);
			org.jfree.data.xy.XYSeries xyseries1 = DatasetUtilities.sampleFunction2DToSeries(normal, (mean + 1 * sd),
					(mean + 2 * sd), 100, "Below Average");
			xyseriescollection.addSeries((XYSeries) xyseries1);
			Function2D normal2 = new NormalDistributionFunction2D(mean, sd);
			org.jfree.data.xy.XYSeries xyseries2 = DatasetUtilities.sampleFunction2DToSeries(normal, (mean + 2 * sd),
					(mean + 3 * sd), 100, "Poor");
			xyseriescollection.addSeries((XYSeries) xyseries2);
			Function2D normal3 = new NormalDistributionFunction2D(mean, sd);
			org.jfree.data.xy.XYSeries xyseries3 = DatasetUtilities.sampleFunction2DToSeries(normal, (mean - 2 * sd),
					(mean + 1 * sd), 100, "Exceeding Expectations");
			xyseriescollection.addSeries((XYSeries) xyseries3);
			Function2D normal4 = new NormalDistributionFunction2D(mean, sd);
			org.jfree.data.xy.XYSeries xyseries4 = DatasetUtilities.sampleFunction2DToSeries(normal, (mean - 3 * sd),
					(mean - 2 * sd), 100, "Outstanding");
			xyseriescollection.addSeries((XYSeries) xyseries4);
			final JFreeChart chart = ChartFactory.createXYLineChart("Standard Normal Distribution", "X", "Y",
					xyseriescollection, PlotOrientation.VERTICAL, true, true, false);
			final ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

			setContentPane(chartPanel);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(BellCurve.class.getName()).log(Level.SEVERE, null, ex);

		}

	}
	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available
	// *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing frech
	// *
	// ****************************************************************************

	/**
	 * Starting point for the demonstration application.
	 *
	 * 
	 * 
	 */

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param
	 * @throws java.sql.SQLException
	 */

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param args
	 * @param <error>
	 * @throws java.sql.SQLException
	 */
	/**
	 * @param args standard main function parameter
	 * @throws SQLException exception while executing statements in constructor
	 */
	public static void main(String[] args) throws SQLException {

		final BellCurve demo = new BellCurve("Normal Distribution Demo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}
}