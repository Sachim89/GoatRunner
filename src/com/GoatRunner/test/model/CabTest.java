package com.GoatRunner.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.GoatRunner.model.Cab;

/**
 * Test cases for all the functionalities related to Cab class.
 * @author Apoorva, Jui, Rishitha, Saranya
 *
 */

public class CabTest {
	/**Test to verify the functionalities for assigning a cab id*/
	@Test
	public void testSetCabId() {
		Cab c = new Cab();
		c.setCab_id(123);
		assertEquals(c.getCab_id(), 123);
	}
	
	/**Test to verify the functionalities for getting a cab id*/
	@Test
	public void testGetCabId() {
		Cab c = new Cab();
		c.setCab_id(123);
		int result= c.getCab_id();
		assertEquals(result, 123);
	}
	
	/**Test to verify the functionalities for assigning the current capacity of a cab*/
	@Test
	public void testSetCapacity() {
		Cab c = new Cab();
		c.setCurrent_capacity(5);
		assertEquals(c.getCurrent_capacity(), 5);
	}
	
	/**Test to verify the functionalities for getting the current capacity of a cab*/
	@Test
	public void testGetCapacity() {
		Cab c = new Cab();
		c.setCurrent_capacity(5);
		int result =c.getCurrent_capacity();
		assertEquals(result, 5);
	}
	
	

}
