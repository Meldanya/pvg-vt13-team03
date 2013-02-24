/**
 * 
 */
package unit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sorting.Lap;
import sorting.SorterConfig;

import com.google.gson.Gson;

public class TestLapConfig {

	private List<Lap> laps;
	private SorterConfig cfg;
	private Gson gson;
	
	@Before
	public void setUp() throws Exception {
		laps = new ArrayList<Lap>();
		cfg = new SorterConfig();
		gson = new Gson();
		
	}

	//Jag vet att det inte är något test sådär, men jag la i ett test mest för att ha ett litet exempel någonstans
	@Test
	public void testLapConfig() throws Exception{
		
		Lap lap1 = new Lap();
		lap1.setFactor(5);
		lap1.setMinimumMinutes(5);
		lap1.setNumber(1);
		laps.add(lap1);
		
		Lap lap2 = new Lap();
		lap2.setFactor(10);
		lap2.setMinimumMinutes(10);
		lap2.setNumber(2);
		laps.add(lap2);
		
		Lap lap3 = new Lap();
		lap3.setFactor(20);
		lap3.setMinimumMinutes(15);
		lap3.setNumber(3);
		laps.add(lap3);
		
		cfg.setProperty("laps", gson.toJson(laps));
		
//		System.out.println("Strängen ser ut såhär:\n" + gson.toJson(laps));
		
		
		List<Lap> jsonLaps = Arrays.asList(gson.fromJson(cfg.getProperty("laps"), Lap[].class));
		
		assertTrue("Not same size", jsonLaps.size() == laps.size());
		
		for(int i = 0; i < jsonLaps.size(); i++){
			assertTrue("wrong factor", jsonLaps.get(i).getFactor() == laps.get(i).getFactor());
			assertTrue("wrong min minutes", jsonLaps.get(i).getMinimumMinutes() == laps.get(i).getMinimumMinutes());
			assertTrue("wrong nbr", jsonLaps.get(i).getNumber() == laps.get(i).getNumber());
		}
		
		System.out.println("För den nyfikna:\n" + gson.toJson(laps));
		
		
	}



}

